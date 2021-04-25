package com.example.demo;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;



@RequestMapping("api/v1/")
@RestController
public class UrlShortener {
    final int SHORT_ID_LENGTH =6;

    @Autowired
    private UrlRepository urlRepository;



    @PostMapping("data/shorten")
    public String shorten(@RequestBody String longURL) throws UnsupportedEncodingException{
        UrlValidator urlValidator = new UrlValidator(new String[]{"http","https"});
        String shortURL = "";
        if(urlValidator.isValid(longURL)){
            if( urlRepository.findByLongUrl(longURL) != null){ // URL exists in db
                shortURL = (urlRepository.findByLongUrl(longURL)).shortUrl;
            } else{  // does not exist in db, generate an unique id & save it to db
                long uniqSnowflakeID = DemoApplication.seqcr.nextId();
                Url new_url = new Url(uniqSnowflakeID, longURL);
                urlRepository.save(new_url);
                shortURL = new_url.shortUrl;
            }
        } // to-do: meaninful response
        
        return shortURL;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUrl(@PathVariable String id){
        Url url = urlRepository.findByShortUrl(id);
        String last=  url.toString();
        System.out.println("URL Retrieved: " + last);
        //return last;
        //UR    I
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

    /*@PostMapping
    public String create(@RequestBody String url){
        UrlValidator urlValidator = new UrlValidator(new String[]{"http","https"});
        if(urlValidator.isValid(url)){
            String shortId=  RandomStringUtils.randomAlphanumeric(6);
            System.out.println("URL ID generated "+ shortId);
            String result = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);

            if ( ( result.charAt(result.length() -1))== '=' ){
                System.out.println("LAST CHARACTER IS = \n");
                result = result.substring(0,result.length()-1);
            }
            System.out.println("Saving " + result  +" to database" + " result string  is = "+ result);
            //urlRepository.save(new Url(shortId,result));
            return shortId;
        }
        throw new RuntimeException("Invalid URL "+ url);
    }*/

}
