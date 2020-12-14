package com.expore.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/*
 @author xinrongliao
 @date 20201213
 操作jedis的工具类
 */
public class RedisUtil {
    //redis 中列表key的名称
    public static String highkey="spider.highlevel";
    public static String lowkey="spider.lowlevel";
    public static String starturl="start.url";
    private JedisPool jedisPool=null;

    public RedisUtil(){
        JedisPoolConfig poolConfig= new JedisPoolConfig();

        //控制一个pool可以分配多少个jedis实例，通过pool.getResponse()来获取；
        //如果赋值为-1，则表示不限制，如果pool已经分配了maxActive个jedis实例，
        //则此时pool的状态为exhausted（耗尽）
        poolConfig.setMaxTotal(100);

        //控制一个pool最多有多少个状态为idle(空闲的)jedis实例
        poolConfig.setMaxIdle(10);

        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException
        poolConfig.setMaxIdle(1000*10);

        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        poolConfig.setTestOnBorrow(true);
        //redis未设置密码：
        jedisPool =new JedisPool(poolConfig,"121.196.190.165",6379,1000*20,"123456");
    }

    /**
     * 获取指定范围的记录，可以为分页使用
     * * @param key String
     *      * @param start long
     *      * @param end long
     *      * @return List
     */

    public List<String> lrange(String key, long start, long end){
        Jedis resource = jedisPool.getResource();
        List<String> list = resource.lrange(key,start,end);
        jedisPool.returnResourceObject(resource);
        return list;
    }

    public void add(String key,String url){
        Jedis resource = jedisPool.getResource();
        resource.lpush(key,url);
        jedisPool.returnResourceObject(resource);
    }

    public String poll(String key){
        Jedis resource =jedisPool.getResource();
        String result=resource.rpop(key);
        jedisPool.returnResourceObject(resource);
        return result;
    }

    public static void main(String[] args) {
        RedisUtil redisUtil = new RedisUtil();
        String url = "https://v.qq.com/channel/tv?_all=1&channel=tv&listpage=1&sort=18&year=859";
        redisUtil.add(starturl,url);
    }
}
