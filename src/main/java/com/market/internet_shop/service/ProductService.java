package com.market.internet_shop.service;

import com.market.internet_shop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    void  addToUserBucket(Long productId, String username);
}
