package com.kanlon.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
//Jedis.jar下载地址：http://central.maven.org/maven2/redis/clients/jedis/2.9.0/jedis-2.9.0.jar
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
//http://commons.apache.org/proper/commons-pool/download_pool.cgi  要引入commons-pool包 ，因为JedisPoolConfig内部继承了该包的类

/**
 * redis连接工具类
 *
 * @author zhangcanlong
 * @date 2018年9月8日
 */
public class JedisPoolUtil {
	private static JedisPool pool = null;
	static {
		// 加载配置文件
		InputStream in = JedisPoolUtil.class.getResourceAsStream("redis.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("加载文件失败");
		}
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig = new JedisPoolConfig();
		// 最大链接数
		poolConfig.setMaxTotal(Integer.parseInt(pro.get("redis.maxTotal").toString()));
		// 最大空闲连接数
		poolConfig.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));
		// 最小空闲连接数
		poolConfig.setMinIdle(Integer.parseInt(pro.get("redis.minIdle").toString()));
		String host = pro.get("redis.host").toString();
		int port = Integer.parseInt(pro.get("redis.port").toString());
		int timeout = Integer.parseInt(pro.get("redis.timeout").toString());
		String password = pro.get("redis.password").toString();

		pool = new JedisPool(poolConfig, host, port, timeout, password);
	}

	public static Jedis getJedis() {
		return pool.getResource();
	}

	public static void release(Jedis jedis) {
		if (null != jedis) {
			jedis.close();
		}
	}

}
