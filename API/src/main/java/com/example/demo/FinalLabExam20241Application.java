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
public class FinalLabExam20241Application {

	public static void main(String[] args) {
		SpringApplication.run(FinalLabExam20241Application.class, args);
	}

}
