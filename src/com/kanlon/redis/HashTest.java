package com.kanlon.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * hashset类型的测试类
 *
 * @author zhangcanlong
 * @date 2018年9月8日
 */
public class HashTest {
	public Jedis jedis = JedisPoolUtil.getJedis();

	// 调用hset
	@Test
	public void fun() {
		Long num = jedis.hset("hset1", "username", "zhangsansan");
		System.out.println(num);
		String hget = jedis.hget("hset1", "username");
		System.out.println(hget);
	}

	// 调用hmset，直接存储map集合
	@Test
	public void fun1() {
		Map<String, String> map = new HashMap<>();
		map.put("username", "张三三");
		map.put("age", "25");
		map.put("sex", "男");
		String res = jedis.hmset("hash2", map);
		System.out.println(res);// ok
		System.out.println(jedis.hgetAll("hash2"));
	}

	// 删除hash中的键，可以删除一个，也可以删除多个，返回的是删除的个数
	@Test
	public void fun2() {
		Long num = jedis.hdel("hash2", "username", "age");
		System.out.println(num);
		Map<String, String> map2 = new HashMap<>();
		map2 = jedis.hgetAll("hash2");
		System.out.println(map2);
		System.out.println("jedis.hgetAll(\"hash2\")--" + jedis.hgetAll("hash2"));
	}

	// 增加hash中的键值对
	@Test
	public void fun3() {
		Map<String, String> map2 = new HashMap<>();
		map2 = jedis.hgetAll("hash2");
		System.out.println(map2);
		jedis.hincrBy("hash2", "age", 10);
		map2 = jedis.hgetAll("hash2");
		System.out.println(map2);
	}

	// 判断hash是否存在某个值
	@Test
	public void fun4() {
		System.out.println(jedis.hexists("hash2", "username"));
		System.out.println(jedis.hexists("hash2", "age"));

		// 获取hash键值对的个数
		System.out.println("=====获取hash键值对的个数====");
		System.out.println(jedis.hlen("hash2"));

	}

	// 获取一个hash中所有的key或value值
	@Test
	public void fun5() {
		Set<String> hash2keys = jedis.hkeys("hash2");
		System.out.println("====获取了所有key值====");
		System.out.println(hash2keys);

		List<String> hash2Value = jedis.hvals("hash2");
		System.out.println("====获取了所有value值====");
		System.out.println(hash2Value);
	}

}
