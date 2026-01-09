package com.springProject.boot;

import com.springProject.boot.Bucket4j.RateLimitProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
		System.out.println("we running");
	}

}
