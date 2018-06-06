package com.shortURL.demo.service;

import java.util.List;

import com.shortURL.demo.model.ShortURL;

public interface ShortURLsService {

    List<ShortURL> findAll();

    ShortURL findOne(String id);

    ShortURL saveShortURLs(String url);

    ShortURL updateShortURLs(ShortURL shortURL);

    ShortURL findByShortURL(String url);
}
