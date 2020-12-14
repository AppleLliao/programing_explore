package com.expore.util;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/*
 @author xinrongliao
 @date 20201213
 */
public class PageDownLoadUtil {

    public static String getPageContent(String url) throws IOException{
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        // httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        // httpget.setHeader("Accept-Encoding","gzip, deflate");
        // httpget.setHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        // httpget.setHeader("Host","gkzy.lnzsks.com");
        // httpget.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
        CloseableHttpResponse response =httpClient.execute(httpGet);
        HttpEntity httpEntity= response.getEntity();
        String htmlGet= EntityUtils.toString(httpEntity,"utf-8");
        httpGet.releaseConnection();
        return htmlGet;
    }


   /* public static void main(String args[]){
        try {
            System.out.println(PageDownLoadUtil.getPageContent("http://www.ccu.edu.cn/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
