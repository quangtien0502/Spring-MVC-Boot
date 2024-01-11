package com.ra.springbootdemomvc.model.repository;

import com.ra.springbootdemomvc.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
