package days;

import java.util.concurrent.TimeUnit;

/*
 同步代码块中的语句越少越好，即细粒度比粗粒度锁效率高
 比较m1和m2
 */
public class Threads016 {
    int count =0;

    synchronized  void m1(){
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //业务逻辑中只有下面语句需要syn,这时不应该给整个方法上锁
        count++;

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        long end= System.currentTimeMillis();
    }

      void m2(){
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //业务逻辑中只有下面语句需要syn,这时不应该给整个方法上锁
          synchronized (this){
            count++;
          }

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        long end= System.currentTimeMillis();
    }
}
