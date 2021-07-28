package com.dingtalk.callbackdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class CallbackDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallbackDemoApplication.class, args);
	}

}
