package com.ra.springbootdemomvc.controller;

import com.ra.springbootdemomvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;



}