package com.jzz.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author:jzz
 * @date:2020/6/27
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage{
    @XStreamAlias("ArticleCount")
    private String articleCount;

    @XStreamAlias("Articles")
    private List<Article> articles = new ArrayList<>();

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public NewsMessage(Map<String, String> map, String articleCount, List<Article> articles) {
        super(map);
        this.setMsgType("news");
        this.articleCount = articleCount;
        this.articles = articles;
    }
}
