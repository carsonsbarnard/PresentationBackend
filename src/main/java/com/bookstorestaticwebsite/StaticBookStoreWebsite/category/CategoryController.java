package com.bookstorestaticwebsite.StaticBookStoreWebsite.category;


import com.bookstorestaticwebsite.StaticBookStoreWebsite.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/all")
    public String getAllCategory(Model model){
        List<Category> categoryList = service.getAllCategories();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("title", "Category List");
        return  "admin/category-list";
    }

    @PostMapping("/createCategory")
    public String createCategory(Category category){
        service.createNewCategory(category);
        return "redirect:/admin/category/all";
    }

    @GetMapping("/createCategoryForm")
    public String createNewUser(Model model){
        model.addAttribute("title", "Create New Category");
        return "admin/category-form";
    }

    @PostMapping("/edit/{cId}")
    public String updateCategory(@PathVariable int cId, @ModelAttribute Category category) {
        service.updateCategory(category, cId);
        return "redirect:/admin/category/all";
    }


    @GetMapping("/edit/{cId}")
    public String editCategory(@PathVariable int cId, Model model){
        //Get user by and display it first
        model.addAttribute("category", service.getCategoryById(cId));
        model.addAttribute("title", "Edit Category");
        return "admin/category-edit";
    }
    @GetMapping("/delete/{cId}")
    public String deleteCategory(@PathVariable int cId){
        service.deleteCategoryById(cId);
        return "redirect:/admin/category/all";
    }


}
