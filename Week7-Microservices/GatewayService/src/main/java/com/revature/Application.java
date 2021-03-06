package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.revature.filters.LoggingFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class Application {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	// we will eventually register a bean here
	@Bean
	public LoggingFilter loggingFilter() {
		return new LoggingFilter();
	}

}
