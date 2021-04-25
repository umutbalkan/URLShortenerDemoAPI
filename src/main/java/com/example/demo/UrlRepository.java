package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url,String> {
    public Url findByShortUrl(String shortUrl);

    public Url findByLongUrl(String longUrl);

    public Url findByUniqueID(Long uid);
}
