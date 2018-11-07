package com.wangz.service;

import com.wangz.entity.Product;
import com.wangz.entity.User;

public interface ProductDubboService {
	Product findProductById(Long productId);
}
