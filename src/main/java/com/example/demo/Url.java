package com.example.demo;

import org.springframework.data.annotation.Id;

import io.seruco.encoding.base62.Base62;

import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
public class Url {
    @Id
    public String id;

    public long uniqueID;
    public String shortUrl;
    public String longUrl;

    public Url () {}

    private Base62 base62;
    private byte[] encoded;
    public Url(long id, String longUrl) throws UnsupportedEncodingException{
        base62 = Base62.createInstance();
        encoded = base62.encode(BigInteger.valueOf(id).toByteArray());
        this.shortUrl = new String(encoded);
        this.longUrl=longUrl;
        this.uniqueID = id;
    }


    @Override
    public String toString() {
        /*
        return String.format(
                "Url[id=%s, shortUrl= '%s', longUrl='%s']",id,shortUrl,longUrl
        );

         */
        return longUrl;
    }
}
