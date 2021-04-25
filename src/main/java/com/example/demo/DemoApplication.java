package com.example.demo;

import java.math.BigInteger;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.seruco.encoding.base62.Base62;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private UrlRepository repository;

	public static Snowflake seqcr = new Snowflake();
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String... args)throws Exception{
		//repository.deleteAll();
		long uid_google = seqcr.nextId();
		long uid_youtube = seqcr.nextId();
		//repository.save(new Url(uid_google,"https://www.google.com/"));
		repository.save(new Url(uid_youtube,"https://www.youtube.com/"));
		System.out.println(seqcr.toString());

				
		Base62 base62 = Base62.createInstance();
		System.out.println("URL found with uniqueID " + uid_youtube + ":");
		System.out.println("--------------------------------");
		System.out.println(repository.findByUniqueID(uid_youtube));
		byte[] encoded = base62.encode(BigInteger.valueOf(uid_youtube).toByteArray());
		String shorturl_youtube = new String(encoded);
		System.out.println("URL found with shortURL " + shorturl_youtube + ":");
		System.out.println("--------------------------------");
		System.out.println(repository.findByShortUrl(shorturl_youtube));
	}

}
