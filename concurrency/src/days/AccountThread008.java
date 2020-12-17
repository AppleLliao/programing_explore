package days;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题
 */
public class AccountThread008 {
    private String name;
    private double balance;

    //写方法
    public synchronized void set(String name,double balance){
        this.name=name;
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        this.balance = balance;
    }

    //读方法
    public /*synchronized*/  double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args){
        AccountThread008 account =new AccountThread008();
        new Thread(()->account.set("goHead",100.0)).start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(account.getBalance("goHead")); //不加synchronized输出的是0.0，加的话输出100.0

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(account.getBalance("goHeard"));
    }
}
