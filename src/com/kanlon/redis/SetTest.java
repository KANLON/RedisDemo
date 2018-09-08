package com.kanlon.redis;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 测试set集合类型 set集合与list类的区别就是set中不会出现重复的数据
 * 它中不会出现重复的数据，进行聚合操作效率比较高，其他操作基本上与List相同
 *
 * @author zhangcanlong
 * @date 2018年9月8日
 */
public class SetTest {

	public Jedis jedis = JedisPoolUtil.getJedis();

	@Test
	public void testSet() {
		System.out.println("预处理：删除已存在的set集合\n");
		System.out.println(jedis.del("myset", "myset1", "myset2", "myset3"));
		System.out.println("======添加，删除元素======\n");
		Long num = jedis.sadd("myset", "a", "a", "b", "abc");
		System.out.println(num);

		System.out.println("======获得元素======\n");
		Set<String> myset1 = jedis.smembers("myset");
		System.out.println(myset1);

		System.out.println("======移除元素======\n");
		jedis.srem("myset", "a", "b");
		Set<String> myset2 = jedis.smembers("myset");
		System.out.println(myset2);

		System.out.println("======判断元素是否存在该set中======\n");
		Boolean sismember = jedis.sismember("myset", "a");
		System.out.println(sismember);

		System.out.println("======获得A-B，差集合======\n");
		jedis.sadd("myset1", "123", "32", "abc", "def", "123456", "adfasd");
		jedis.sadd("myset2", "abc", "345", "123", "fda");
		Set<String> sdiff = jedis.sdiff("myset1", "myset2");
		System.out.println(sdiff);

		System.out.println("======获得交集======\n");
		Set<String> sinter = jedis.sinter("myset1", "myset2");
		System.out.println(sinter);

		System.out.println("======获得并集合======\n");
		Set<String> sunion = jedis.sunion("myset1", "myset2");
		System.out.println(sunion);

		System.out.println("======成员数量======\n");
		System.out.println(jedis.scard("myset1"));

		System.out.println("======随机获得一个成员======\n");
		System.out.println(jedis.srandmember("myset1"));

		System.out.println("======将相差的成员放到一个新的set中，同理交集和并集都可以，后面加上一个store即可，并返回新的长度======\n");
		System.out.println(jedis.sdiffstore("myset3", "myset1", "myset2"));
		System.out.println(jedis.smembers("myset3"));
	}

}
