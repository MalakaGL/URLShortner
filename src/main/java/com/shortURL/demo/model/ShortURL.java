package com.shortURL.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShortURL {
    @Id
    private String url;

    private String shortURL;
    private int usage;
    private Date lastAccessed;

    public ShortURL() {
    }

    public ShortURL(String url, String shortURL) {
        this.url = url;
        this.shortURL = shortURL;
        this.usage = 0;
        this.lastAccessed = new Date();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public Date getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Date lastAccessed) {
        this.lastAccessed = lastAccessed;
    }
}
