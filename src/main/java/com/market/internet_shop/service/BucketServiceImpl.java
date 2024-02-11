package com.market.internet_shop.service;

import com.market.internet_shop.dao.BucketRepository;
import com.market.internet_shop.dao.ProductRepository;
import com.market.internet_shop.domain.Bucket;
import com.market.internet_shop.domain.Product;
import com.market.internet_shop.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final ProductRepository productRepository;
    private final BucketRepository bucketRepository;

    public BucketServiceImpl(ProductRepository productRepository, BucketRepository bucketRepository) {
        this.productRepository = productRepository;
        this.bucketRepository = bucketRepository;
    }


    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productsIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productsList = getCollectRefProductsById(productsIds);
        bucket.setProducts(productsList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsById(List<Long> productsIds) {
        return productsIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> productsIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductsList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductsList.addAll(getCollectRefProductsById(productsIds));
        bucket.setProducts(newProductsList);
        bucketRepository.save(bucket);
    }


}
