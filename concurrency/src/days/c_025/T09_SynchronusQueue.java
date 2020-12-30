package days.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 当向SynchronousQueue插入元素时，必须同时有个线程往外取
 * SynchronousQueue是没有容量的，这是与其他的阻塞队列不同的地方
 */
public class T09_SynchronusQueue { //容量为0
    public static void main(String[] args)throws  InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try{
                System.out.println("thread:"+strs.take()); //aaa
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); //阻塞等待消费者消费
        //strs.add("aaa");//报错
        System.out.println("main thread:"+strs.size());
    }
}
