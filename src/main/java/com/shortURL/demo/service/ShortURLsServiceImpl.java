package com.shortURL.demo.service;

import com.shortURL.demo.dao.ShortURLRepository;
import com.shortURL.demo.model.ShortURL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShortURLsServiceImpl implements ShortURLsService {

    @Autowired
    private ShortURLRepository shortURLRepository;

    @Override
    public List<ShortURL> findAll() {
        return shortURLRepository.findAll();
    }

    @Override
    public ShortURL findOne(String id) {
        return shortURLRepository.findOne(id);
    }

    @Override
    public ShortURL saveShortURLs(String url) {
        String shortURL = UUID.randomUUID().toString().substring(0, 8);
        ShortURL s = findByShortURL(shortURL);
        while(s != null) {
            shortURL = UUID.randomUUID().toString().substring(0, 8);
            s = findByShortURL(shortURL);
        }
        return shortURLRepository.save(new ShortURL(url, shortURL));
    }

    @Override
    public ShortURL updateShortURLs(ShortURL shortURL) {
        return shortURLRepository.save(shortURL);
    }

    @Override
    public ShortURL findByShortURL(String shortURL) {
        return shortURLRepository.findByShortURL(shortURL);
    }
}
