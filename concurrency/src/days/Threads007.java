package days;

/**
 * 同步和非同步方法是否可以同时调用
 */
public class Threads007 {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"m1 start...");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m1 end");
    }

    public void m2(){
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2");
    }

    public static void main(String[] args){
        Threads007 threads007 = new Threads007();
        new Thread(()-> threads007.m1(),"t1").start();
        new Thread(()->threads007.m2(),"t2").start();
    }
}
