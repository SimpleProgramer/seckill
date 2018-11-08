package com.wangz.dao;

import com.stip.mybatis.generator.plugin.GenericMapper;
import com.wangz.entity.Order;
import com.wangz.entity.example.OrderExample;

 /**
 * 可添加自定义查询语句，方便后续扩展
 **/
public interface OrderDao extends GenericMapper<Order, OrderExample, Long> {
}