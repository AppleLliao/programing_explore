package days;
//被synchroized修饰的部分不是原子操作，不被synchronized修饰的部分不是原子操作

public class Threads005 implements Runnable{

    private int count =10;

    @Override
    public /*synchronized*/void run() {
        count--;//不是原子操作
        System.out.println(Thread.currentThread().getName()+" count = "+count);
//        Thread0 count = 9
//        Thread4 count = 5
//        Thread3 count = 6
//        Thread2 count = 7
//        Thread1 count = 8

//加上synchronized之后，一定是：
//        Thread0 count = 9
//        Thread1 count = 8
//        Thread2 count = 7
//        Thread3 count = 6
//        Thread4 count = 5

    }

    public static void main(String args[]){
        Threads005 threads005=new Threads005();
        for(int i=0;i<5; i++){
            new Thread(threads005,"Thread"+i).start();
        }
    }
}
