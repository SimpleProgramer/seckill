package com.wangz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wangz.dao.UserDao;
import com.wangz.entity.User;
import com.wangz.entity.example.UserExample;
import com.wangz.service.UserDubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wangzun
 * @Desc user生产服务
 * @version 2018/11/7 上午10:58
 **/
@Service(version = "1.0.0")
public class UserDubboServiceImpl implements UserDubboService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findUserByUserId(Long userId) {
		return Optional.of(userDao.selectByPrimaryKey(userId)).orElse(null);
	}

}
