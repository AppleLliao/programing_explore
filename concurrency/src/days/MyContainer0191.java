package days;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：
 * 实现一个容器，提供两个方法：add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个小时，线程2给出提示并结束
 * 分析下面这个程序，能完成这个功能
 */
public class MyContainer0191 {
    List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String args[]){
        MyContainer0191 myContainer0191= new MyContainer0191();

        new Thread(()->{
            for(int i=0;i<10;i++){
                myContainer0191.add(new Object());
                System.out.println("add "+i);

                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            while(true){//一直会循环，当size为5时也不会停止，因为两个线程数据不见，t2永远不会结束
                if(myContainer0191.size()==5){
                    break;
                }

            }
            System.out.println("t2结束");
        }).start();
    }
}



/**
 * 曾经的面试题：
 * 实现一个容器，提供两个方法：add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 给lists添加volatile之后，t2能够接到通知，但是t2线程的死循环很浪费cpu，如果不用死循环怎么做呢？
 *
 * 分析下面这个程序，能完成这个功能吗？
 */

 class MyContainer0192 {
     //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String args[]){
        MyContainer0192 myContainer0192= new MyContainer0192();

        new Thread(()->{
            for(int i=0;i<10;i++){
                myContainer0192.add(new Object());
                System.out.println("add "+i);

                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            while(true){//一直会循环，当size为5时也不会停止，因为两个线程数据不见，t2永远不会结束
                if(myContainer0192.size()==5){
                    break;
                }

            }
            System.out.println("t2结束");
        }).start();
    }
}


/**
* 给lists添加volatile之后，t2能够接到通知，但是t2线程的死循环很浪费cpu，如果不用死循环怎么做呢？
        *
        * 这里使用wait和notify可以做到，wait会释放锁，而notify不会释放锁
        * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
        *
        * 阅读下面程序，并分析出结果
        * 可以读到输出结果但并不是sieze=5时退出，而是t1结束时t2才接收到通知而退出
*/

 class MyContainer0193 {
    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String args[]){
        MyContainer0193 myContainer0193= new MyContainer0193();
        final Object lock =new Object();

        new Thread(()->{
            synchronized (lock){
                System.out.println("t2启动");
                if(myContainer0193.size()!=5){
                    try{
                        lock.wait(); // 阻塞同时释放锁
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
            }
        },"t2").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e){
            e.printStackTrace();
        }


        new Thread(()->{
            System.out.println("t1启动");
            synchronized (lock){
                for(int i=0; i<10;i++){
                    myContainer0193.add(new Object());
                    System.out.println("add"+i);

                    if(myContainer0193.size()==5){
                        lock.notify();  //唤醒这把锁上等待的线程，但不会释放锁
                    }
                }
            }
            System.out.println("t1结束");
        },"t1").start();
    }
}

/**
 * * notify之后,t1必须释放锁，t2退出后也必须notify，通知t1继续执行
 *  * 整个通信过程比较繁琐
 */

class MyContainer0194 {
    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String args[]){
        MyContainer0194 myContainer0194= new MyContainer0194();
        final Object lock =new Object();

        new Thread(()->{
            synchronized (lock){
                System.out.println("t2启动");
                if(myContainer0194.size()!=5){
                    try{
                        lock.wait(); // 阻塞同时释放锁
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");

                //让t1继续执行
                lock.notify(); //这里不需要wait是不是这个线程执行完了呢？自动把锁给t1
            }
        },"t2").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e){
            e.printStackTrace();
        }


        new Thread(()->{
            System.out.println("t1启动");
            synchronized (lock){
                for(int i=0; i<10;i++){
                    myContainer0194.add(new Object());
                    System.out.println("add"+i);

                    if(myContainer0194.size()==5){
                        lock.notify();  //唤醒这把锁上等待的线程，但不会释放锁

                        try {
                            lock.wait();//释放锁，让t2得以执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1结束");
        },"t1").start();
    }
}

/**
 *  * notify之后,t1必须释放锁，t2退出后也必须notify，通知t1继续执行
 *  * 整个通信过程比较繁琐
 *  *
 *  * 使用Latch(门闩)替代wait notify来进行通知
 *  * 好处是通信方式简单，同时也可以指定等待时间
 *  * 使用await和countdown方法替代wait和notify
 *  * CountDownLatch不涉及锁定，当count的值为零是当前线程继续运行
 *  * 当不涉及同步，只是涉及线程通信的时候，用synchronized+wait/notify就显得太重了
 *  * 这时应该考虑CountDownLacth/cyclicbarrier/semaphore
 */

class MyContainer0195 {
    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String args[]){
        MyContainer0195 myContainer0195= new MyContainer0195();
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(()->{

            System.out.println("t2启动");
            if(myContainer0195.size()!=5){
                try{
                    latch.await();
                    //也可以指定等待时间
                    //latch.await(5000,TimeUnit.MILLISECONDS);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("t2结束");

        },"t2").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e){
            e.printStackTrace();
        }


        new Thread(()->{
            System.out.println("t1启动");

            for(int i=0; i<10;i++){
                myContainer0195.add(new Object());
                System.out.println("add"+i);

                if(myContainer0195.size()==5){
                    //门闩数量减一，变为0之后打开门闩，让t2得以执行
                    latch.countDown();
                }
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("t1结束");
        },"t1").start();
    }
}