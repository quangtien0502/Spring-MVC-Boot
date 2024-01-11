package com.ra.springbootdemomvc.controller;

import com.ra.springbootdemomvc.model.entity.Category;
import com.ra.springbootdemomvc.model.entity.Product;
import com.ra.springbootdemomvc.service.CategoryService;
import com.ra.springbootdemomvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Value("${path-upload}")
    private String pathUpload;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    //todo: list quantity
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
    public String addProduct(@ModelAttribute("product") Product product,@RequestParam("img") MultipartFile file){
        String fileName=file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),new File(pathUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImage(fileName);
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
        List<Category> categories=categoryService.getAll();
        model.addAttribute("product",product);
        model.addAttribute("categories",categories);
        return "product/update-product";
    }

    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute Product product){
        Product product1=productService.save(product);
        Long categoryId=product1.getCategory().getId();
        return "redirect:/product/"+categoryId;
    }

}
