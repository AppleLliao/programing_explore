package main.java.com.expore.service;

import main.java.com.expore.entity.Page;

import java.io.IOException;

/*
 @author xinrongliao
 @date 20201213
 页面下载接口
 */
public interface PageDownLoadService {
    public Page downLoad(String url) throws IOException;
}
