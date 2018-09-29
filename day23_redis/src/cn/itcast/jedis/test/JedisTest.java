package cn.itcast.jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class JedisTest {
    /**
     * jedis使用
     */
    @Test
    public void test1(){
        //1.创建连接对象
        Jedis jedis = new Jedis("localhost", 6379);
        //2.操作数据 操作方式和redis一样
        jedis.set("username","liuyifei");
        String username = jedis.get("username");
        System.out.println(username);
        //3.关闭
        jedis.close();

    }

    /**
     * jedis连接池
     */
    @Test
    public void test2(){
        //1.创建配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        //2.创建连接池对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);
        //3.获取连接
        Jedis jedis = jedisPool.getResource();
        //4.操作数据

        //5.关闭
        jedis.close();
    }
}
