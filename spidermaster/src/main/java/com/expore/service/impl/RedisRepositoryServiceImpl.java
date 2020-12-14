package com.expore.service.impl;

import com.expore.service.RepositoryService;
import com.expore.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;

//redis实现url数据仓库，1.避免程序挂了队列的数据也没了 2.在多节点场景进行共享
public class RedisRepositoryServiceImpl implements RepositoryService {

    RedisUtil redisUtil= new RedisUtil();

    @Override
    public String poll() {
        String url= redisUtil.poll(RedisUtil.highkey);
        if(StringUtils.isBlank(url)){
            url=redisUtil.poll(RedisUtil.lowkey);
        }
        return url;
    }

    @Override
    public void addHighLevel(String url) {
        redisUtil.add(RedisUtil.highkey,url);
    }

    @Override
    public void addLowLevel(String url) {
        redisUtil.add(RedisUtil.lowkey,url);
    }
}
