import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {
	
	JedisPool pool;
	Jedis jedis;
	
	@Before
	public void SetUp(){
		pool = new JedisPool(new JedisPoolConfig(), "192.168.1.118");
		jedis = pool.getResource();
	}
	
	@Test
	public void testSetString(){
		jedis.set("name", "jjzz");
		System.out.println(jedis.get("name"));
		
		jedis.append("name", "tree");
		System.out.println(jedis.get("name"));
		
		jedis.set("name", "zzjj");
		System.out.println(jedis.get("name"));
		
		jedis.mset("name","zg","password","zz");
		System.out.println(jedis);
		
		jedis.del("name");
		System.out.println(jedis.get("name"));
	}
	
	@Test
	public void testSetMap(){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "zz");
		
		//jedis.set("map", map);
		
		
	}
	
	@Test
	public void testRedisLock(){
		
		//jedis.setnx("name", "zz");
		//long lock = jedis.setnx("pp", "cc");
		//System.out.println(lock);
		
		
		long getLock = jedis.expire("pp", 1000);
		System.out.println(getLock);
	}
	
}
