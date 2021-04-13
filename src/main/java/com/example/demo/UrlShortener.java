package com.example.demo;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;



@RequestMapping("rest/url")
@RestController
public class UrlShortener {
    final int SHORT_ID_LENGTH =6;

    @Autowired
    private UrlRepository urlRepository;


    @GetMapping("/{id}")
    public ResponseEntity<?> getUrl(@PathVariable String id){
        Url url = urlRepository.findByShortUrl(id);
        String last=  url.toString();
        System.out.println("URL Retrieved: " + last);
        //return last;
        //URI
        //HttpHeaders
        try {
            URI uri = new URI(last);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
        }catch (Exception e){
            System.out.println (e.getMessage());
            return null;

        }

    /*
    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id){
        Url url = urlRepository.findByShortUrl(id);
        String last=  url.toString();
        System.out.println("URL Retrieved: " + last);
        //return last;
        //URI
        //HttpHeaders
        return last;

    */


        /*
        URI uri = new URI(redirect.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
        */
    }

    @PostMapping
    public String create(@RequestBody String url){
        UrlValidator urlValidator = new UrlValidator(new String[]{"http","https"});

        if(true){
            String shortId=  RandomStringUtils.randomAlphanumeric(6);
            System.out.println("URL ID generated "+ shortId);
            String result = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);

            if ( ( result.charAt(result.length() -1))== '=' ){
                System.out.println("LAST CHARACTER IS = \n");
                result = result.substring(0,result.length()-1);
            }
            System.out.println("Saving " + result  +" to database" + " result string  is = "+ result);
            urlRepository.save(new Url(shortId,result));
            return shortId;
        }
        throw new RuntimeException("Invalid URL "+ url);
    }

}
