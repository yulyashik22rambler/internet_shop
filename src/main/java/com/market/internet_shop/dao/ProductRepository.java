package com.market.internet_shop.dao;

import com.market.internet_shop.domain.Product;
import com.market.internet_shop.domain.User;
import com.market.internet_shop.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
