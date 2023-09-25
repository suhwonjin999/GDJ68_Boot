package com.winter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableAspectJAutoProxy
@EnableScheduling
public class Gdj68BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gdj68BootApplication.class, args);
	}

}
