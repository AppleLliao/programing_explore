package main.java.com.expore.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*
 @author xinrongliao
 @date 20201213
 */
public class LoadPropertyUtil {
    public static String getValue(String key){
        String value=null;
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
            //遍历取值
            value = resourceBundle.getString(key);
        }catch (MissingResourceException e){
            e.printStackTrace();
        }
    return value;
    }

    public static void main(String[] args){
        String value=getValue("threadNum");
        System.out.println("value="+value);
    }
}
