package days;

//最简单的锁模式，使用synchronized锁，锁的是堆内存的中对象
public class Synchronized001 {
    private int count = 10;
    //为了得到一把锁，new出来一个对象，不值得
    private Object o=new Object();

    public void m(){
        synchronized (o){ //任何线程要执行下面的代码，必须先拿到o的锁
            count--;
            System.out.println(Thread.currentThread().getName()+"count = "+count);
        }
    }
}
