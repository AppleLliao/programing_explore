package days;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 有n张火车票，每张票都有一个编号，同时有10个
 * 窗口对外售票，请写一个模拟程序
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售
 */
public class TicketSeller0241 {
    static List<String> tickets= new ArrayList<>();

    static{
        for(int i=0;i<100;i++){  //加100张票
            tickets.add("票编号："+i);
        }
    }

    public static void main(String args[]){
        for(int i=0;i<10;i++){
            new Thread(()->{
                while(tickets.size()>0){//可能会没票还在卖，报错
                    /*try{
                        TimeUnit.MILLISECONDS.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }*/
                    //System.out.println(Thread.currentThread().getName()+"销售--"+tickets.remove(0));
                    System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0)+"    当前余票："+tickets.size());
                }
            },"窗口"+i).start();
        }
    }
}
