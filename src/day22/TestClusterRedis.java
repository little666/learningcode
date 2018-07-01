/**
 * 
 */
package day22;

import java.util.HashSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhangchao
 * @date   2018年5月24日
 * 
 */
public class TestClusterRedis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		HashSet<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort("192.168.43.121",7001));
		jedisClusterNode.add(new HostAndPort("192.168.43.121",7002));
		jedisClusterNode.add(new HostAndPort("192.168.43.121",7003));
		jedisClusterNode.add(new HostAndPort("192.168.43.121",7004));
		jedisClusterNode.add(new HostAndPort("192.168.43.121",7005));
		jedisClusterNode.add(new HostAndPort("192.168.43.121",7006));
		
		JedisPoolConfig cfg = new JedisPoolConfig();
		cfg.setMaxTotal(100);
		cfg.setMaxIdle(20);
		cfg.setMaxWaitMillis(-1);
		cfg.setTestOnBorrow(true);
		JedisCluster jc = new JedisCluster(jedisClusterNode,6000,100,cfg);
		
		System.out.println(jc.set("age", "20"));
		System.out.println(jc.set("sex", "nv"));
		System.out.println(jc.set("name", "firewall"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("name"));
		System.out.println(jc.get("age"));
		System.out.println(jc.get("sex"));
		
		
		
	}

}
