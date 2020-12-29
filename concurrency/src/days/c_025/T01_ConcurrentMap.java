package days.c_025;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * https://blog.csdn.net/sunxianghuang/article/details/52221913
 * 阅读ConcurrentSkipListMap
 */
public class T01_ConcurrentMap {
    public static void main(String args[]){
        Map<String,String> map=new ConcurrentHashMap<>(); //耗时97
        //Map<String,String> map=new ConcurrentSkipListMap(); //高并发并且排序 耗时107

        //Map<String,String> map = new Hashtable<>(); //耗时95
        //Map<String,String> map= new HashMap<>(); //耗时101

        Random r=new Random();
        Thread[] ths=new Thread[100]; //放100个线程
        CountDownLatch latch = new CountDownLatch(ths.length); //https://www.cnblogs.com/Lee_xy_z/p/10470181.html
        long start=System.currentTimeMillis();
        for(int i=0;i<ths.length;i++){
            ths[i]=new Thread(()->{
                for(int j=0;j<1000;j++){
                    map.put("a"+r.nextInt(100000),"a"+r.nextInt(100000));
                    latch.countDown();
                }
            });
        }

        Arrays.asList(ths).forEach(t->t.start());
        try{
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
