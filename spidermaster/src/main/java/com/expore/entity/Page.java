package main.java.com.expore.entity;

import java.util.ArrayList;
import java.util.List;

/*
 存储页面信息实体类
 @author xinrongliao
 @date 20201213
 */
public class Page {

    //页面内容
    private String content;
    //评分
    private String score;
    //总播放量
    private String allNumber;
    //评论数
    private String commentNumber;
    //踩
    private String againstNumber;
    //电视剧名称
    private String tvName;
    //详情页面url
    private String url;
    //集数
    private String episodeNumber;

    //包含列表url和详情页url；
    private List<String> urlList=new ArrayList<>();

    public void addUrl(String url){
        urlList.add(url);
    }


    @Override
    public String toString() {
        return "Page{" +
                "content='" + content + '\'' +
                ", score='" + score + '\'' +
                ", allNumber='" + allNumber + '\'' +
                ", commentNumber='" + commentNumber + '\'' +
                ", againstNumber='" + againstNumber + '\'' +
                ", tvName='" + tvName + '\'' +
                ", url='" + url + '\'' +
                ", episodeNumber='" + episodeNumber + '\'' +
                ", urlList=" + urlList +
                '}';
    }
}
