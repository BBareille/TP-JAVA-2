package com.tpjava.tpjava2.controller;

import com.tpjava.tpjava2.entity.Category;
import com.tpjava.tpjava2.entity.Level;
import com.tpjava.tpjava2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        return "category/categoryList";
    }

    @GetMapping("/add")
    public String getForm(Model model)
    {
        Category category = new Category();
        model.addAttribute("category", category);

        return "category/categoryForm";
    }

    @GetMapping("/modify/{id}")
    public String updateForm(@PathVariable String id, Model model)
    {
        Optional<Category> category = categoryRepository.findById(Long.valueOf(id));
        model.addAttribute("category", category);

        return "category/categoryForm";
    }

    @PostMapping("/post")
    public String postForm(@ModelAttribute Category category, Model model, RedirectAttributes redirectAttributes)
    {
        if(category.getId() != null){
            redirectAttributes.addFlashAttribute("success", "Catégorie modifié");
        } else {
            redirectAttributes.addFlashAttribute("success", "Catégorie ajouté");
        }
        try {
            categoryRepository.save(category);
        } catch (Exception e){
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenu");
        }

        return "redirect:";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
    {
        Optional<Category> category = categoryRepository.findById(Long.valueOf(id));
        category.ifPresent(value -> categoryRepository.delete(value));

        redirectAttributes.addFlashAttribute("success", "Une catégorie a été supprimé");

        return "redirect:";
    }
}
