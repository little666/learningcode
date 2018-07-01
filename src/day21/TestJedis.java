/**
 * 
 */
package day21;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import util.FastJsonConvert;

/**
 * @author zhangchao
 * @date   2018年4月26日
 * 
 */
public class TestJedis {
	
	@Test
	public void test(){
		Jedis jedis = new Jedis("192.168.43.121",6379);
		
		Map<String,String> map = new HashMap<String,String>();
		//取出age=32 
		//取出age=32 and sex=w
		
		//多种集合配合使用  hash 和 set 类型同时使用
		
		//指定业务  查询业务：SYS_USER_SEL_AGE_32
		//指定业务 查询业务： SYS_USER_SEL_SEX_m
		//指定业务 查询业务： SYS_USER_SEL_SEX_w
		
		final String SYS_USER_TABLE = "SYS_USER_TABLE";
		final String SYS_USER_SEL_AGE_32 = "SYS_USER_SEL_AGE_32";
		final String SYS_USER_SEL_SEX_m = "SYS_USER_SEL_SEX_m";
		final String SYS_USER_SEL_SEX_w = "SYS_USER_SEL_SEX_w";
		
		
		/*
		//放入操作
		//UUID
		String u1id = UUID.randomUUID().toString();
		User u1 = new User(u1id,"zhang3",30,"m");
		map.put(u1id, FastJsonConvert.convertObjectToJSON(u1));
		jedis.sadd(SYS_USER_SEL_SEX_m, u1id);
		
		String u2id = UUID.randomUUID().toString();
		User u2 = new User(u2id,"zhang4",32,"w");
		map.put(u2id, FastJsonConvert.convertObjectToJSON(u2));
		jedis.sadd(SYS_USER_SEL_SEX_w, u2id);
		jedis.sadd(SYS_USER_SEL_AGE_32, u2id);
		
		String u3id = UUID.randomUUID().toString();
		User u3 = new User(u3id,"zhang5",22,"w");
		map.put(u3id, FastJsonConvert.convertObjectToJSON(u3));
		jedis.sadd(SYS_USER_SEL_SEX_w, u3id);
		
		String u4id = UUID.randomUUID().toString();
		User u4 = new User(u4id,"zhang6",55,"m");
		map.put(u4id, FastJsonConvert.convertObjectToJSON(u4));
		jedis.sadd(SYS_USER_SEL_SEX_m, u4id);
		
		String u5id = UUID.randomUUID().toString();
		User u5 = new User(u5id,"zhang7",54,"w");
		map.put(u5id, FastJsonConvert.convertObjectToJSON(u5));
		jedis.sadd(SYS_USER_SEL_SEX_w, u5id);
		
		String u6id = UUID.randomUUID().toString();
		User u6 = new User(u6id,"zhang8",32,"m");
		map.put(u6id, FastJsonConvert.convertObjectToJSON(u6));
		jedis.sadd(SYS_USER_SEL_SEX_m, u6id);
		jedis.sadd(SYS_USER_SEL_AGE_32, u6id);
		
		String u7id = UUID.randomUUID().toString();
		User u7 = new User(u7id,"zhang9",27,"w");
		map.put(u7id, FastJsonConvert.convertObjectToJSON(u7));
		jedis.sadd(SYS_USER_SEL_SEX_w, u7id);
		
		String u8id = UUID.randomUUID().toString();
		User u8 = new User(u8id,"zhang10",38,"w");
		map.put(u8id, FastJsonConvert.convertObjectToJSON(u8));
		jedis.sadd(SYS_USER_SEL_SEX_w, u8id);
		
		String u9id = UUID.randomUUID().toString();
		User u9 = new User(u9id,"zhang11",19,"w");
		map.put(u9id, FastJsonConvert.convertObjectToJSON(u9));
		jedis.sadd(SYS_USER_SEL_SEX_w, u9id);
		
		String u10id = UUID.randomUUID().toString();
		User u10 = new User(u10id,"zhang2",6,"m");
		map.put(u10id, FastJsonConvert.convertObjectToJSON(u10));
		jedis.sadd(SYS_USER_SEL_SEX_m, u10id);
		
		jedis.hmset("SYS_USER_TABLE", map);
		*/
		
		//1,取age32的记录
		Set<String> user_ages = jedis.smembers(SYS_USER_SEL_AGE_32);
		for(Iterator iterator = user_ages.iterator();iterator.hasNext();){
			String string = (String) iterator.next();
			String ret = jedis.hget(SYS_USER_TABLE, string);
			User user = FastJsonConvert.convertJSONToObject(ret, User.class);
			System.out.println(user);
		}
		
		//2，取age32 and sex = w 的记录
		//先取两个业务集合的交集
		System.out.println("-------------分割-------------------");
		Set<String> resultSet = jedis.sinter(SYS_USER_SEL_AGE_32,SYS_USER_SEL_SEX_w);
		for(Iterator iterator = resultSet.iterator();iterator.hasNext();){
			String string = (String) iterator.next();
			String ret = jedis.hget(SYS_USER_TABLE, string);
			User user = FastJsonConvert.convertJSONToObject(ret, User.class);
			System.out.println(user);
		}
		
	}
}
