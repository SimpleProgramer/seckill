package com.wangz.service;

import com.wangz.models.resp.ApiResponse;

public interface ProductDubboService {

    ApiResponse<Integer> deductInventory(Long productId);
}
