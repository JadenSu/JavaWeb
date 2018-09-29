package cn.itcast.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * JedisPool工具类
 * 加载配置文件
 * 提供获取连接的方法
 */
public class JedisPoolUtils2 {
    //避免重复创建对象
    private static JedisPool jedisPool;

    //加载配置文件
    static {
        //读取配置文件
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建Properties对象
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取配置数据
        int maxTotal = Integer.parseInt(pro.getProperty("maxTotal"));
        int maxIdle = Integer.parseInt(pro.getProperty("maxIdle"));
        String host = pro.getProperty("host");
        int port = Integer.parseInt(pro.getProperty("port"));
        //创建配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        //初始化 JedisPool
        jedisPool = new JedisPool(jedisPoolConfig, host, port);

    }
}
