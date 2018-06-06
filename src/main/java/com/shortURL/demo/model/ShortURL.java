package com.shortURL.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShortURL {

    @Id
    private String url;

    @Column(length=10000)
    private String shortURL;

    public ShortURL() {
    }

    public ShortURL(String url, String shortURL) {
        this.url = url;
        this.shortURL = shortURL;
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
}
