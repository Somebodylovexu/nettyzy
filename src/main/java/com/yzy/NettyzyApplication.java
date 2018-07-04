package com.yzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
public class NettyzyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyzyApplication.class, args);
	}
}
