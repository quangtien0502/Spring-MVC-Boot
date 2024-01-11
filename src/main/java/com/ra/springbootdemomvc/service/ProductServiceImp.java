package com.ra.springbootdemomvc.service;

import com.ra.springbootdemomvc.model.entity.Category;
import com.ra.springbootdemomvc.model.entity.Product;
import com.ra.springbootdemomvc.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductByCategoryId(Long category_id) {
        return productRepository.findProductByCategoryId(category_id);
    }


}
