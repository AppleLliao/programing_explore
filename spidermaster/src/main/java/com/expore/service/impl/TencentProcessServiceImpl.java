package com.expore.service.impl;

import com.expore.entity.Page;
import com.expore.service.PageProcessService;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class TencentProcessServiceImpl implements PageProcessService {
    private String xpathScore = "//*[@id='container_player']/div[2]/div[2]/div[1]/div[1]/span/span[3]/span/span";
    private String allNumber = "//*[@id=\"mod_cover_playnum\"]";
    private String tvName = "//*[@id=\"container_player\"]/div[2]/div[2]/div[1]/div[1]/h1/a";
    private String episodeNumber = "//*[@id=\"container_player\"]/div[2]/div[2]/div[1]/div[2]/div/div[2]";
    private String eachDetailUrl = "//div[@class='list_item']/a";
    @Override
    public void process(Page page) {
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode rootNode = htmlCleaner.clean(page.getContent());
        if(page.getUrl().startsWith("https://v.qq.com/x/cover")){
            //解析电视剧详情页
            System.out.println("开始解析电视剧详情页！");
            parseDetail(page,rootNode);
        }else{
            //解析列表
            System.out.println("开始解析电视剧列表页！");
            String nowUrl= page.getUrl();
            //这里获取的是下一个年份的列表地址
            String nextUrl="https://v.qq.com/channel/tv?_all=1&channel=tv&listpage=1&sort=18&year="+(Integer.parseInt(nowUrl.substring(nowUrl.length()-3))+1);

            if(nextUrl !=null){
                page.addUrl(nextUrl);
                System.out.println("添加下一页成功："+nextUrl);
            }
            try{
                Object[] objects = rootNode.evaluateXPath(eachDetailUrl);
                if(objects.length>0){
                    for(Object o:objects){
                        String detailUrl=((TagNode)o).getAttributeByName("href");
                        page.addUrl(detailUrl);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void parseDetail(Page page, TagNode rootNode) {
        try{
            TagNode scoreNode =(TagNode)rootNode.evaluateXPath(xpathScore)[0];
            page.setScore(scoreNode.getText().toString());

            TagNode allNumberNode = (TagNode)rootNode.evaluateXPath(allNumber)[0];
            page.setAllNumber(allNumberNode.getText().toString());

            TagNode tvNameNode =(TagNode)rootNode.evaluateXPath(tvName)[0];
            page.setTvName(tvNameNode.getText().toString());

            TagNode episodeNumberNode = (TagNode)rootNode.evaluateXPath(episodeNumber)[0];
            page.setEpisodeNumber(episodeNumberNode.getText().toString());

            page.setContent(null);
        }catch (XPatherException e){
            e.printStackTrace();
        }
    }
}
