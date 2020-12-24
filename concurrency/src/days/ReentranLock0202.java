package days;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * reentranLock用于替代synchronized
 * 需要注意的是，必须要手动释放锁
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁释放
 */
public class ReentranLock0202 {

    Lock lock = new ReentrantLock();

    void m1(){
        /**
         * 在try-finally 外加锁的话，如果因为发生异常导致加锁失败
         * try-finally块中的代码不会执行
         * 相反，如果在try{}代码块中加锁失败，finally中的代码无论如何都会执行
         * 但是由于当前线程加锁失败并没有持有lock对象锁，程序会抛出异常
         *
         */
        lock.lock(); //相当于synchronized（this）
        try{
            for(int i=0;i<10;i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1:"+i);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();//释放锁
        }
    }

    void m2(){
        lock.lock();
        try{
            System.out.println("m2.......");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String args[]){
        ReentranLock0202 r1=new ReentranLock0202();
        new Thread(r1::m1).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(r1::m2).start();
    }
}
