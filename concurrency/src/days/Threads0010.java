package days;

import java.util.concurrent.TimeUnit;

/**
 * 一个不同方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候任然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的
 * 这里是继承中可能发生的情形，子类调用父类方法
 */
public class Threads0010 {
    synchronized  void m(){
        System.out.println("m start");
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("m end");
    }


}
    class  Thread0010 extends Threads0010{
        synchronized  void m(){
            System.out.println("child m start");
            super.m(); //调用父类的m方法，父类的m方法得到的锁是子类的
            System.out.println("chile m end");
        }
    }
