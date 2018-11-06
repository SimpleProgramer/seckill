package com.wangz.service;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wangz.entity.User;

@Component
public class UserConsumerService {

	@Reference(version = "1.0.0")
	private UserDubboService userDubboService;

	public User findUser(String name) {
		User user = userDubboService.findUserByName(name);
		System.out.println(user.getUserId() + "." + user.getPassword() + "." + user.getUserName());
		return user;
	}

}
