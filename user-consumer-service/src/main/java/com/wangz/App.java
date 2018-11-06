package com.wangz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.wangz.service.UserConsumerService;

/**
 * Hello world!
 *
 */
/**
 * @author Nick
 *
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
		UserConsumerService service = run.getBean(UserConsumerService.class);
		service.findUser("Who am I");
	}
}
