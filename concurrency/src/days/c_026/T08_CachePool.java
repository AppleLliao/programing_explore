package days.c_026;

import java.lang.ref.SoftReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//弹性线程池，来一个任务如果没有线程空闲的情况下就起一个新的线程，默认如果
//线程空闲60s没任务来的话就会自动销毁
public class T08_CachePool {
    public static void main(String args[])throws InterruptedException{
        ExecutorService service= Executors.newCachedThreadPool();
        System.out.println(service);

        for(int i=0;i<2;i++){
            service.execute(()->{
                try{
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);
        TimeUnit.SECONDS.sleep(80);
        System.out.println(service);
    }
}
