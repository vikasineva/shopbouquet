package com.ua.rosella.rest;

import com.ua.rosella.model.Product;
import com.ua.rosella.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class CatalogRestController {
    @Autowired
    ProductService productService;

    @GetMapping("/{transliteration}")
    public Product showProduct(@PathVariable String transliteration) {
        Product product = productService.getProductByTranslit(transliteration);
        return product;
    }
}
