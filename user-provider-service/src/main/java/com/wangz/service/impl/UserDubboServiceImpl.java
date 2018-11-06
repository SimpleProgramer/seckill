package com.wangz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wangz.dao.UserDao;
import com.wangz.entity.User;
import com.wangz.entity.example.UserExample;
import com.wangz.service.UserDubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(version = "1.0.0")
public class UserDubboServiceImpl implements UserDubboService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findUserByName(String name) {

		UserExample example = new UserExample();
		example.createCriteria().andUserNameEqualTo(name);
		List<User> user = userDao.selectByExample(example);
		return Optional.ofNullable(user).orElse(new ArrayList<>()).get(0);
	}

}
