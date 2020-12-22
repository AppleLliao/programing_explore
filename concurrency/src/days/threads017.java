package days;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某个对象0，如果0的属性发生改变，不影响锁的使用
 * 但如果0变成另外一个对象，则锁定的对象发生改变
 * 应该避免锁定对象的引用变成另外的对象
 */
class Threads017 {
      Object o=new Object();

    void m(){
        synchronized (o){
            while(true){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args){
        Threads017 threads017 = new Threads017();

        //启动第一个线程
        new Thread(threads017::m,"t1").start();

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //创建第二个线程
        Thread t2 = new Thread(threads017::m,"t2");
        //threads017.o=new Object();   //锁发生改变，所以t2线程得以执行，如果注释掉这句话，线程2永远得不到执行机会
        t2.start();
    }
}
