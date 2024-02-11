package com.market.internet_shop.service;

import com.market.internet_shop.domain.Bucket;
import com.market.internet_shop.domain.User;

import javax.persistence.Id;
import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productsIds);

    void addProducts(Bucket bucket, List<Long> productsIds);
}
