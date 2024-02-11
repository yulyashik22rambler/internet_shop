package com.market.internet_shop.mapper;

import com.market.internet_shop.domain.Product;
import com.market.internet_shop.dto.ProductDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER= Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDTO dto);

    @InheritConfiguration
    ProductDTO fromProduct(Product product);

    List<Product> toProductList(List<ProductDTO> productDTOs);

    List<ProductDTO> fromProductList(List<Product> products);
}
