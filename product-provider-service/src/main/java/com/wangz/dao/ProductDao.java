package com.wangz.dao;

import com.stip.mybatis.generator.plugin.GenericMapper;
import com.wangz.entity.Product;
import com.wangz.entity.example.ProductExample;

 /**
 * 可添加自定义查询语句，方便后续扩展
 **/
public interface ProductDao extends GenericMapper<Product, ProductExample, Long> {
}