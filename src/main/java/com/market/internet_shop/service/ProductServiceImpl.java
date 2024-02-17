package com.market.internet_shop.service;

import com.market.internet_shop.dao.ProductRepository;
import com.market.internet_shop.domain.Bucket;
import com.market.internet_shop.domain.User;
import com.market.internet_shop.dto.ProductDTO;
import com.market.internet_shop.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper = ProductMapper.MAPPER;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;
    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }


    @Override
    public List<ProductDTO> getAll() {
        return productMapper.fromProductList(productRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user==null)
        {throw  new RuntimeException("User not found!" + username);}
        Bucket bucket = user.getBasket();
        if (bucket==null)
        {
           Bucket newBucket =bucketService.createBucket(user, Collections.singletonList(productId));
           user.setBasket(newBucket);
           userService.save(user);
        }else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }
}
