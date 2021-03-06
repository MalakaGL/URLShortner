package com.shortURL.demo.dao;

import com.shortURL.demo.model.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortURLRepository extends JpaRepository<ShortURL, String> {
    @Query("select s from ShortURL s where s.shortURL = :shortURL")
    ShortURL findByShortURL(@Param("shortURL") String shortURL);
}
