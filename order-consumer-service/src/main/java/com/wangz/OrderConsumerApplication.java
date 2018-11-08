package com.wangz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wangz.dao")
public class OrderConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderConsumerApplication.class, args);
	}
}
