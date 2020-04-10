package io.github.sarangolestani.domain.vo;

import java.util.Date;

public class TwitterPostVO {

    private Date publishDate;
    private String link;
    private String title;
    private String content;

    public TwitterPostVO() {
    }

    public TwitterPostVO( Date publishDate, String link, String title, String content) {
        this.publishDate = publishDate;
        this.link = link;
        this.title = title;
        this.content = content;
    }


    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
