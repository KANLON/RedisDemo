package com.kanlon.redis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * zset 有序集合的测试类，字符串成员（member）和浮点分值（score）之间的有序映射 如果分数行相同则按成员名排序，
 *
 * @author zhangcanlong
 * @date 2018年9月8日
 */
public class SortSetTest {
	public Jedis jedis = JedisPoolUtil.getJedis();

	@Test
	public void testZset() {
		System.out.println("=========删除指定zset==========\n");
		System.out.println(jedis.del("myzset"));

		System.out.println("=======添加元素=======\n");
		jedis.zadd("myzset", 100.0, "张三");
		jedis.zadd("myzset", 200, "lisi");
		jedis.zadd("myzset", 50, "wangwu");
		Map<String, Double> map = new HashMap<>();
		map.put("mutouliu", 70.0);
		jedis.zadd("myzset", map);
		System.out.println(jedis.zrange("myzset", 0, -1));

		System.out.println("=======获取元素=======\n");
		System.out.println(jedis.zrange("myzset", 0, -1));
		// 获取某个元素的浮点分值
		System.out.println(jedis.zscore("myzset", "lisi"));
		System.out.println("=======获取指定分值范围的元素=======\n");
		System.out.println(jedis.zrangeByScore("myzset", 49, 100));

		System.out.println("=======删除元素=======\n");
		jedis.zrem("myzset", "张三");
		System.out.println(jedis.zrange("myzset", 0, -1));

	}
}
