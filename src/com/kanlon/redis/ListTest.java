package com.kanlon.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 列表list数据类型测试
 *
 * @author zhangcanlong
 * @date 2018年9月8日
 */
public class ListTest {
	public Jedis jedis = JedisPoolUtil.getJedis();

	@Test
	public void testList() {
		System.out.println("=======添加一个list元素=====\n");
		jedis.lpush("collections", "ArrayList", "Vestor", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
		jedis.lpush("collections", "HashSet");
		jedis.lpush("collections", "TreeSet");
		jedis.lpush("collections", "TreeMap");

		System.out.println("=======获取list元素=====\n");
		// -1代表倒数第一个元素，-2代表倒数第二个元素
		System.out.println("Collections的内容:" + jedis.lrange("collections", 0, -1));
		System.out.println("Collections区间0-3的元素：" + jedis.lrange("collections", 0, 3));
		System.out.println("================");

		System.out.println("=======删除元素=====\n");
		// 删除列表指定的值，第二个参数为删除的个数（有重复时），后add进去的值先被删除，类似于出栈
		System.out.println("删除指定元素个数：" + jedis.lrem("collections", 2, "HashMap"));
		System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1) + "\n");
		System.out.println("删除下表0-3区间之外的元素：" + jedis.ltrim("collections", 0, 3));
		System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1) + "\n");

		System.out.println("=======左右出栈元素=====\n");
		System.out.println("collections列表出栈（左端）" + jedis.lpop("collections"));
		System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1) + "\n");
		System.out.println("collection列表出栈（右端）：" + jedis.rpop("collections"));
		System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1) + "\n");

		System.out.println("=======修改或获取指定下标元素=====\n");
		System.out.println("修改collection指定下标为1的内容：" + jedis.lset("collections", 1, "linkedArraylist"));
		System.out.println("collections的内容为：" + jedis.lrange("collections", 0, -1) + "\n");
		System.out.println("collections的长度为：" + jedis.llen("collections"));
		System.out.println("获取collections下标为2的元素：" + jedis.lindex("collections", 2));

		System.out.println("=======输出排序list元素=====\n");
		jedis.del("sortList");
		jedis.lpush("sortList", "3", "6", "2", "0", "7", "4");
		System.out.println("sortList排序前：" + jedis.lrange("sortList", 0, -1));

		System.out.println(jedis.sort("sortList"));
		System.out.println("sortList排序后（不会变化）：" + jedis.lrange("sortList", 0, -1));

	}
}
