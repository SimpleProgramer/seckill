package com.wangz.service;

import com.wangz.entity.User;

public interface UserDubboService {
	User findUserByName(String name);
}
