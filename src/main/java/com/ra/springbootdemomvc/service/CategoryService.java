package com.ra.springbootdemomvc.service;

import com.ra.springbootdemomvc.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category save(Category category);
    Category findById(Long id);
    void delete(Long id);
}
