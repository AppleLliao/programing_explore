package com.expore.service.impl;

import com.expore.entity.Page;
import com.expore.service.DataStoreService;

public class DataStoreServiceImpl implements DataStoreService {

    @Override
    public void store(Page page) {
        System.out.println(page.toString());
    }
}
