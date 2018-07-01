/**
 * 
 */
package day21;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author zhangchao
 * @date   2018年4月26日
 * 
 */
public class TestSingleRedis {

	private static Jedis jedis;
	private static ShardedJedis shard;
	private static ShardedJedisPool pool;
	
	public static void setUpBeforeClass() throws Exception{
		
		jedis = new Jedis("192.168.1.121",6379);
		
		List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.1.121",6379));
		
		shard = new ShardedJedis(shards);
		
		GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
		goConfig.setMaxTotal(100);
		goConfig.setMaxIdle(20);
		goConfig.setMaxWaitMillis(-1);
		goConfig.setTestOnBorrow(true);
		pool = new ShardedJedisPool(goConfig, shards);
		
	}
	
	public static void tearDownAfterClass()throws Exception{
		jedis.disconnect();
		shard.disconnect();
		
		pool.destroy();
	}
	
	public static void testString(){
		
		jedis.set("name", "zhangsan");
		System.out.println(jedis.get("name"));
		
		jedis.append("name", " 6666");
		System.out.println(jedis.get("name"));
		
		jedis.del("name");
		System.out.println(jedis.get("name"));
		
		jedis.mset("name","lisi","age","23","qq","323232");
		jedis.incr("age");
		System.out.println(jedis.get("name")+"-"+jedis.get("age")+"-"+jedis.get("qq"));
	}
	
	public static void testMap(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "xinxin");
		map.put("age", "64");
		map.put("qq","432311124");
		
		jedis.hmset("user", map);
		
		List<String> rsmap = jedis.hmget("user", "name","age","qq");
		System.out.println(rsmap);
		
		jedis.hdel("user", "age");
		System.out.println(jedis.hmget("user", "age"));
		System.out.println(jedis.hlen("user"));
		System.out.println(jedis.exists("user"));
		System.out.println(jedis.hkeys("user"));
		System.out.println(jedis.hvals("user"));
		
		Iterator<String> iterator = jedis.hkeys("user").iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			System.out.println(key+":"+jedis.hmget("user", key));
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		setUpBeforeClass();
//		testString();
		testMap();
	}
}
