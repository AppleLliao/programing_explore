package days;

//this关键字，锁定调用者对象本身，不用new了，方便很多
public class Synchronized002 {
    private int count=10;

    public void m(){
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+"count="+count);
        }
    }

    //如果一个方法从一开始就要得到锁的话，可以直接将synchronized写到方法上
    public synchronized  void m1(){
        count--;
        System.out.println(Thread.currentThread().getName()+"count="+count);
    }
}
