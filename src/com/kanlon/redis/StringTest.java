package com.kanlon.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * String 类型测试
 *
 * @author zhangcanlong
 * @date 2018年9月8日
 */
public class StringTest {
	public Jedis jedis = JedisPoolUtil.getJedis();

	// 添加和获取值
	@Test
	public void fun() {
		jedis.set("num", "1");
		System.out.println(jedis.get("num"));
	}

	// 删除值
	@Test
	public void fun1() {
		jedis.del("num");
		System.out.println(jedis.get("num"));
	}

	// 自增和自减
	@Test
	public void fun2() {
		jedis.set("num", "1");
		System.out.println(jedis.get("num"));
		jedis.decr("num");
		System.out.println(jedis.get("num"));
		jedis.incr("num");
		jedis.incr("num");
		System.out.println(jedis.get("num"));
	}

	// 加上或减去某个数
	@Test
	public void fun3() {
		Long num = jedis.incrBy("num", 3);
		System.out.println(num);
		jedis.decrBy("num", 10);
		System.out.println(jedis.get("num"));
		jedis.set("name", "zhangsansan");
		jedis.decrBy("name", 1);
	}

	// 字符串拼接
	@Test
	public void fun4() {
		Long len = jedis.append("name", "123");
		System.out.println(len);
		System.out.println(jedis.get("name"));
	}
}
