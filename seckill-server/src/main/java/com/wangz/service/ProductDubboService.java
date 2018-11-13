package com.wangz.service;

import com.wangz.entity.Product;
import com.wangz.models.resp.ApiResponse;

public interface ProductDubboService {
    ApiResponse<Product> findProductById(Long productId);

    ApiResponse<Integer> deductInventory(Long productId);
}
