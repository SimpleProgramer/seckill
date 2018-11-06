package com.wangz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wangz.entity.User;
import com.wangz.service.UserDubboService;

@Service(version = "1.0.0")
public class UserDubboServiceImpl implements UserDubboService {

	@Override
	public User findUserByName(String name) {
		
		User user = new User();
		user.setUserId(123l);
		user.setPassword("123456");
		user.setUserName(name);
		return user;
	}

}
