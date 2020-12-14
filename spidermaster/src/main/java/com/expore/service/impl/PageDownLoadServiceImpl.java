package com.expore.service.impl;

import com.expore.entity.Page;
import com.expore.service.PageDownLoadService;
import com.expore.util.PageDownLoadUtil;

import java.io.IOException;

public class PageDownLoadServiceImpl implements PageDownLoadService {

    @Override
    public Page downLoad(String url) throws IOException {
        Page page=new Page();
        page.setUrl(url);
        page.setContent(PageDownLoadUtil.getPageContent(url));
        return page;
    }
}
