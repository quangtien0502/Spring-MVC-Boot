package com.ra.springbootdemomvc.controller;

import com.ra.springbootdemomvc.model.entity.Category;
import com.ra.springbootdemomvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String category(Model model){
        List<Category> list=categoryService.getAll();
        model.addAttribute("list",list);
        return "category/index";
    }

    @GetMapping("/category/add-category")
    public String addCategory(Model model){
        Category category=new Category();
        model.addAttribute("category",category);
        return "category/add-category";
    }

    @PostMapping("/insertCategory")
    public String insertCategory(@ModelAttribute("category") Category category){
        Category category1=categoryService.save(category);
        if(category1!=null){
            return "redirect:/category";
        }
        return "category/add-category";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
        return "redirect:/category";
    }

    @GetMapping("/updateCategory/{id}")
    public String updateCategory(@PathVariable Long id,Model model){
        model.addAttribute("category",categoryService.findById(id));
        return "category/updateCategory";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        return "redirect:/category";
    }
}
