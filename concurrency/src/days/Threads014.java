package days;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比上个程序，可以同synchronized解决，synchronized可以保证可见性和原子性，
 * volatile 只保证可见性
 */
public class Threads014 {
    int count = 0;
    synchronized  void m(){
        for(int i =0; i<100;i++){
            count++;
        }
    }

    public static void main(String[] args){
        Threads014 threads014 = new Threads014();
        List<Thread> threadList =new ArrayList<>();

        for(int i=0; i<10;i++){
            threadList.add(new Thread(threads014::m,"thread-"+i));
        }

        threadList.forEach((o)->o.start());
        threadList.forEach((o)->{
            try{
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        System.out.println(threads014.count);
    }
}
