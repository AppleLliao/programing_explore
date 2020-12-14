package com.expore.util;

public class ThreadUtil {

    public static void sleep(long millons){
        try{
            Thread.currentThread().sleep(millons);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
