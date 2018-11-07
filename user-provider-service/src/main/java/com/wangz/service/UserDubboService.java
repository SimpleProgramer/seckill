package com.wangz.service;

import com.wangz.entity.User;

public interface UserDubboService {
	User findUserByUserId(Long userId);
}
