package com.ra.springbootdemomvc.controller;

import com.ra.springbootdemomvc.model.entity.Category;
import com.ra.springbootdemomvc.model.entity.Product;
import com.ra.springbootdemomvc.service.CategoryService;
import com.ra.springbootdemomvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product/{category_id}")
    public String product(Model model, @PathVariable Long category_id){
        List<Product> list= productService.findProductByCategoryId(category_id);
        model.addAttribute("category_id",category_id);
        model.addAttribute("list",list);
        return "product/index";
    }

    @GetMapping("/product/add-product/{category_id}")
    public String addProduct(Model model,@PathVariable Long category_id){
        Product product=new Product();
        Category category=categoryService.findById(category_id);
        product.setCategory(category);
        model.addAttribute("product",product);
        return "product/add-product";
    }

    @PostMapping("/product/add-product")
    public String addProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        return "redirect:/category";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product product=productService.findById(id);
        Long categoryId=product.getCategory().getId();
        productService.delete(id);
        System.out.println("redirect:/product/" + categoryId);
        return "redirect:/product/" + categoryId;
    }

    @GetMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id,Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "product/update-product";
    }

}
