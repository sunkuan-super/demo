package com.aspirecn.demo.test;

import net.minidev.json.JSONUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Title: JedisTest
 * @Package: com.aspirecn.demo.test
 * @Description: Jedis测试类
 * @Author: sunkuan
 * @Date: 2020/6/24 - 10:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisTest {

    /**
     * 快速入门
     */
    @Test
    public void test() {
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        //2.操作
        jedis.set("username", "wanglaoban");

        //3.关闭连接
        jedis.close();
    }

    @Test
    public void testString() {
        //1.获取连接
        Jedis jedis = new Jedis();  //如果使用空参构造，默认使用localhost 6379端口

        //2.操作
        jedis.set("username", "张三");

        //3.获取
        String username = jedis.get("username");
        System.out.println(username);

        //可以使用setex()方法存储可以指定过期时间的key value
        jedis.setex("username", 10, "lisi");

        //3.关闭连接
        jedis.close();
    }

    /**
     * hash 数据结构操作
     */
    @Test
    public void testHash() {
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        //2.操作
        jedis.hset("user", "name", "lisi");
        jedis.hset("user", "age", "22");
        jedis.hset("user", "gender", "male");

        //获取hash
        System.out.println(jedis.hget("user", "name"));

        //获取hash的所有map中的数据
        Map<String, String> map = jedis.hgetAll("user");

        // 遍历map的三种方式keySet和entrySet和java8的foreach
//        for (String key:map.keySet()){
//            System.out.println(key + ":" + map.get(key));
//        }

//        for (Map.Entry<String,String> entry: map.entrySet()){
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }

        map.forEach((key, value) -> System.out.println(key + ":" + value));
        jedis.close();
    }

    /**
     * list数据结构操作
     */
    @Test
    public void testList() {
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        //2.操作
        jedis.lpush("mylist", "a", "b", "c");
        jedis.rpush("mylist", "a", "b", "c");

        //查询
        List<String> mylist = jedis.lrange("mylist", 0, -1);

        System.out.println(mylist);

        //删除mylist最左边的一个元素，返回值为被删除的元素
        String a = jedis.lpop("mylist");
        System.out.println(a);

        //3.关闭连接
        jedis.close();
    }

    /**
     * set数据结构操作
     */
    @Test
    public void testSet() {
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        //2.set存储
        jedis.sadd("myset","java","php","c++");

        //set获取
        Set<String> stringSet = jedis.smembers("myset");
        System.out.println(stringSet);

        //3.关闭连接
        jedis.close();
    }

    /**
     * sortedset数据结构操作
     */
    @Test
    public void testSortedSet() {
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        //2.set存储
        jedis.zadd("mysortedset",3,"亚瑟");
        jedis.zadd("mysortedset",50,"后羿");
        jedis.zadd("mysortedset",30,"猴子");

        //获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);
        //3.关闭连接
        jedis.close();
    }

    /**
     * Jedis配置类
     */
    @Test
    public void testJedisPool() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 资源池最大连接数
        jedisPoolConfig.setMaxTotal(50);
        // 连接池中最多可空闲maxIdle个连接 ，这里取值为10，表示即使没有redis连接时依然可以保持20空闲的连接，而不被清除，随时处于待命状
        // 资源池允许最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);
        // 资源池确保最少空闲连接数
        jedisPoolConfig.setMinIdle(2);
        //1.获取连接
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"localhost");

        //2.获取连接
        Jedis jedis = jedisPool.getResource();

        jedis.set("haha","haha");

        //3.关闭 归还到连接池中
        jedis.close();
    }
}
