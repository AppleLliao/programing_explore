package days;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this,只有m2执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 */
public class ReentranLock0201 {
    synchronized  void m1(){
        for(int i=0;i<10;i++){
            try{
                TimeUnit.SECONDS.sleep(1);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("m1:"+i);
        }
    }

    synchronized  void m2(){
        System.out.println("m2..................");
    }

    public static void main(String[] args){
        ReentranLock0201 r1 = new ReentranLock0201();
        new Thread(r1::m1).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(r1::m2).start();
    }
}


