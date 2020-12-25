package days;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock还以指定公平锁，哪个线程等的时间长给哪个线程锁
 */
public class ReentrantLock0205 extends Thread{
    private static ReentrantLock lock=new ReentrantLock(true); //参数为true表示为公平锁，请对比输出结果

    public void run(){
        for(int i=0;i<5;i++){
            lock.lock(); //得到锁
            try{
                try {
                    sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String args[]){
        ReentrantLock0205 r1= new ReentrantLock0205();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();
    }
}
