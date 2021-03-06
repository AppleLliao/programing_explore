package days;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中的session就存在ThreadLocal中，避免synchronized的使用
 */
public class ThreadLocal0222 {
    static ThreadLocal<Person> t1=new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t1.get()); //null,两个线程之间变量互不影响
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //t1.get().name="lala";
            t1.set(new Person());
        }).start();
    }

    static class Person{
        String name="zhangsan";
    }
}
