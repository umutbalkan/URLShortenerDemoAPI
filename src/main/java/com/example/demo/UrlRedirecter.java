package com.example.demo;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RequestMapping("/r/")
@RestController
public class UrlRedirecter {

    @Autowired
    private UrlRepository urlRepository;

    @RequestMapping("{shortUrl}")
    public ResponseEntity<?> getUrl(@PathVariable String shortUrl){
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url != null){
            String longUrl =  url.longUrl;
            System.out.println(shortUrl + " URL Redirected to -> " + longUrl);
            try {
                URI uri = new URI(longUrl);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setLocation(uri);
                return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
            }catch (Exception e){
                System.out.println (e.getMessage());
                return null;
    
            }
        }

        return null;
    }
}
