package com.expore.start;

import com.expore.entity.Page;
import com.expore.service.DataStoreService;
import com.expore.service.PageDownLoadService;
import com.expore.service.PageProcessService;
import com.expore.service.RepositoryService;
import com.expore.service.impl.DataStoreServiceImpl;
import com.expore.service.impl.PageDownLoadServiceImpl;
import com.expore.service.impl.RedisRepositoryServiceImpl;
import com.expore.service.impl.TencentProcessServiceImpl;
import com.expore.util.LoadPropertyUtil;
import com.expore.util.PageDownLoadUtil;
import com.expore.util.ThreadUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName StartTVCount
 * @Description 电视剧爬虫执行入口类
 * @Author xinrongliao
 * @Date 2020/12/13/11:39
 * @Version 1.0
 */
public class StartTVCount {

    private PageDownLoadService pageDownLoadService;

    private PageProcessService pageProcessService;

    private DataStoreService dataStoreService;

    private RepositoryService repositoryService;

    private ExecutorService threadPool = Executors.newFixedThreadPool(Integer.parseInt(LoadPropertyUtil.getValue("threadNum")));

    public static void main(String[] args) throws IOException {
        StartTVCount startTVCount =new StartTVCount();
        startTVCount.setPageDownLoadService(new PageDownLoadServiceImpl());
        startTVCount.setDataStoreService(new DataStoreServiceImpl());
        startTVCount.setPageProcessService(new TencentProcessServiceImpl());
        startTVCount.setRepositoryService(new RedisRepositoryServiceImpl());

        String url = "https://v.qq.com/channel/tv?_all=1&channel=tv&listpage=1&sort=18&year="+859;
        startTVCount.repositoryService.addHighLevel(url);
        //        startTVCount.urlQueue.add(url);
        startTVCount.startSpider();
    }

    public void startSpider()throws IOException{
        while(true){
            final String url=repositoryService.poll();
            if(StringUtils.isNoneBlank(url)){
                //开启多线程同时处理存量url
                threadPool.execute(new Runnable(){
                    @Override
                    public void run() {
                        System.out.println("当前启动第"+Thread.currentThread().getId()+"个线程");
                        Page page =null;
                        try{
                            page = StartTVCount.this.downLoadPage(url); //获取页面
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        processPage(page); //处理页面

                        List<String> urlList = page.getUrlList();
                        for(String eachUrl:urlList){
                            if(eachUrl.startsWith("https://v.qq.com/channel")){
                                repositoryService.addHighLevel(eachUrl);
                                System.out.println("添加列表页至队列成功！" + eachUrl);
                            }else{
                                repositoryService.addLowLevel(eachUrl);
                                System.out.println("添加详情页至队列成功！" + eachUrl);
                            }

                        }

                        if(page.getUrl().startsWith("https://v.qq.com/x/cover")){
                            storeData(page);
                        }
                        ThreadUtil.sleep(Long.parseLong(LoadPropertyUtil.getValue("millions")));
                    }
                });
            }else{
                //队列里没有url先休息会
                ThreadUtil.sleep(Long.parseLong(LoadPropertyUtil.getValue("millions")));
            }
        }
    }

    public void setPageDownLoadService(PageDownLoadService pageDownLoadService) {
        this.pageDownLoadService = pageDownLoadService;
    }

    public void setPageProcessService(PageProcessService pageProcessService) {
        this.pageProcessService = pageProcessService;
    }

    public void setDataStoreService(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }

    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public void setThreadPool(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    public Page downLoadPage(String url) throws IOException{
        return pageDownLoadService.downLoad(url);
    }

    public void processPage(Page page){
        pageProcessService.process(page);
    }

    public void storeData(Page page){
        dataStoreService.store(page);
    }
}
