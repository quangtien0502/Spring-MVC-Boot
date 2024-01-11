package com.ra.springbootdemomvc.controller;

import com.ra.springbootdemomvc.model.entity.Category;
import com.ra.springbootdemomvc.model.entity.Product;
import com.ra.springbootdemomvc.service.CategoryService;
import com.ra.springbootdemomvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/{category_id}")
    public String product(Model model, @PathVariable Long category_id){
        List<Product> list= productService.findProductByCategoryId(category_id);
        model.addAttribute("list",list);
        return "product/index";
    }

}
