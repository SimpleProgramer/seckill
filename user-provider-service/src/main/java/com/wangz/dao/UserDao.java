package com.wangz.dao;

import com.stip.mybatis.generator.plugin.GenericMapper;
import com.wangz.entity.User;
import com.wangz.entity.example.UserExample;
import org.apache.ibatis.annotations.Mapper;

/**
 * 可添加自定义查询语句，方便后续扩展
 **/
public interface UserDao extends GenericMapper<User, UserExample, Long> {
}