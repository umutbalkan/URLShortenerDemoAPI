package com.example.demo;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.RandomStringUtils;



@RequestMapping("rest/url")
@RestController
public class UrlShortener {
    final int SHORT_ID_LENGTH =6;

    @Autowired
    private UrlRepository urlRepository;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id){
        Url url = urlRepository.findByShortUrl(id);
        String last=  url.toString();
        System.out.println("URL Retrieved: " + last);
        return last;

    }

    @PostMapping
    public String create(@RequestBody String url){
        UrlValidator urlValidator = new UrlValidator(new String[]{"http","https"});

        if(true){
            String shortId= shortId = RandomStringUtils.randomAlphanumeric(6);
            System.out.println("URL ID generated "+ shortId);
            urlRepository.save(new Url(shortId,url));
            return shortId;
        }
        throw new RuntimeException("Invalid URL "+ url);
    }

}
