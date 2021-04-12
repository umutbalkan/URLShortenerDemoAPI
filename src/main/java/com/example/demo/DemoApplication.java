package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private UrlRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String... args)throws Exception{
		repository.deleteAll();
		repository.save(new Url("abcdefg","https://www.google.com/"));
		repository.save(new Url("zxcvbnm","https://www.youtube.com/"));

		System.out.println("URL found with shortURL('abcdefg'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByShortUrl("abcdefg"));

	}
}
