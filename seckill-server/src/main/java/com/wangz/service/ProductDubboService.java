package com.wangz.service;

import com.wangz.entity.Product;

public interface ProductDubboService {
	Product findProductById(Long productId);
}
