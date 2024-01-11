package com.ra.springbootdemomvc.model.repository;

import com.ra.springbootdemomvc.model.entity.Category;
import com.ra.springbootdemomvc.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
