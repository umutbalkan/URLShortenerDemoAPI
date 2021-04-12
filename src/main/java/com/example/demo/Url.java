package com.example.demo;

import org.springframework.data.annotation.Id;

public class Url {
    @Id
    public String id;

    public String shortUrl;
    public String longUrl;

    public Url () {}

    public Url(String shortUrl, String longUrl){
        this.shortUrl=shortUrl;
        this.longUrl=longUrl;
    }
    @Override
    public String toString() {
        return String.format(
                "Url[id=%s, shortUrl= '%s', longUrl='%s']",id,shortUrl,longUrl
        );
    }
}
