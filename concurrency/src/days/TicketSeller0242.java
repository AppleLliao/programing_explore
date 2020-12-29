package days;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller0242 {
    static Vector<String> tickets = new Vector<>();

    static{
        for(int i=0;i<1000;++i){
            tickets.add("票编号："+i);
        }
    }

    public static void main(String args[]){
        for(int i=0;i<10;i++){
            new Thread(()->{
                while(tickets.size()>0){
                    try{
                        TimeUnit.MILLISECONDS.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    //会出现ArrayIndexOutOfBoundException异常，
                    //当还有一张票时，10个线程都判断了size>0进行售卖，但其实只有一张票
                    System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0));
                }
            },"窗口"+i).start();
        }
    }
}
