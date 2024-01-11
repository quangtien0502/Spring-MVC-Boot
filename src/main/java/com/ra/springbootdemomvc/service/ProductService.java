package com.ra.springbootdemomvc.service;

import com.ra.springbootdemomvc.model.entity.Category;
import com.ra.springbootdemomvc.model.entity.Product;

import java.util.List;

public interface ProductService{
    List<Product> getAll();
    Product save(Product product);
    Product findById(Long id);
    void delete(Long id);
}
