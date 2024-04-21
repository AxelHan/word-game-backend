package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
/*@CrossOrigin(origins = "http://localhost:4200")*/
public class WordGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordGameApplication.class, args);
	}

}
