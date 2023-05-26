package com.ua.rosella.service;

import com.ua.rosella.model.Product;
import com.ua.rosella.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product getProductByTranslit(String translit) {
        return productRepository.findByTransliteration(translit);
    }
}
