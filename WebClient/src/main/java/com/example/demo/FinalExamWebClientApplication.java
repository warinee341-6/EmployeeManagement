package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages="com.example.demo")
@Configuration
@EnableTransactionManagement
public class FinalExamWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalExamWebClientApplication.class, args);
	}

}
