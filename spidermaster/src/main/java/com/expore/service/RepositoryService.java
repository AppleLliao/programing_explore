package com.expore.service;

/**
 * 存储url仓库接口
 */
public interface RepositoryService {

    String poll();
    void addHighLevel(String url);
    void addLowLevel(String url);
}
