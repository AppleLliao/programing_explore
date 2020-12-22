package days;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
 解决同样的问题的更高效的方法，使用atom类
 atomicxxx类本身方法都是原子性的，但不能保证多个方法连续调用
 是原子性的
 */
public class Threads015 {
    AtomicInteger count = new AtomicInteger(0);
    void m(){
        for(int i=0; i<10000;i++){
            count.incrementAndGet(); //原子操作，替代count++的
        }
    }

    public static void main(String[] args){
        Threads015 thread015 = new Threads015();
        List<Thread> threads = new ArrayList<>();

        for(int i=0;i<10;i++){
            threads.add(new Thread(thread015::m,"thread-"+i));
        }

        threads.forEach((o)->o.start());
        threads.forEach((o)->{
            try{
                o.join();//主要作用就是同步,它可以使得线程之间的并行执行变为串行执行
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        System.out.println(thread015.count);//结果一定是100000，因为incrementAndGet是原子操作
    }
}
