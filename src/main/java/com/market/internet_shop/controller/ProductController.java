package com.market.internet_shop.controller;

import com.market.internet_shop.dto.ProductDTO;
import com.market.internet_shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public  String list(Model model){
        List<ProductDTO> list = productService.getAll();
        model.addAttribute("products",list);
        return "products";
    }
}
