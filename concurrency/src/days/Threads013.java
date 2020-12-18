package days;

import java.util.ArrayList;
import java.util.List;

/*
 volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 */
public class Threads013 {
    volatile  int count =0;
    void m(){
        for(int i= 0;i<1000;i++){
            count++;
        }
    }

    public static void main(String[] args){
        Threads013 thread013= new Threads013();

        List<Thread> threads = new ArrayList<>();
        for(int i=0;i<5;i++){
            threads.add(new Thread(thread013::m,"thread+"+i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try{
                o.join(); //join主要作用就是让主线程等待子线程执行完毕后，才让主线程继续执行
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        System.out.println(thread013.count); //如果结果时5000，说明volatile不是原子操作
    }
}
