package com.tpjava.tpjava2.controller;

import com.tpjava.tpjava2.entity.Category;
import com.tpjava.tpjava2.entity.Level;
import com.tpjava.tpjava2.entity.Training;
import com.tpjava.tpjava2.repository.CategoryRepository;
import com.tpjava.tpjava2.repository.LevelRepository;
import com.tpjava.tpjava2.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class TrainingController {

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/")
    public String trainingList(Model model){
        List<Training> trainingList = trainingRepository.findAll();
        model.addAttribute("TrainingList", trainingList);
        return "training/TrainingList";
    }

    @GetMapping("/add")
    public String getForm(Model model)
    {
        List<Level> levelList = levelRepository.findAll();
        List<Category> categoryList = categoryRepository.findAll();

        Training training = new Training();

        model.addAttribute("levelList", levelList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("training", training);

        return "training/trainingForm";
    }

    @GetMapping("/modify/{id}")
    public String updateForm(@PathVariable String id, Model model)
    {
        Optional<Training> training = trainingRepository.findById(Long.valueOf(id));
        List<Level> levelList = levelRepository.findAll();
        List<Category> categoryList = categoryRepository.findAll();

        model.addAttribute("levelList", levelList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("training", training);
        return "training/trainingForm";
    }

    @PostMapping("/post")
    public String postForm(@ModelAttribute Training training, Model model, RedirectAttributes redirectAttributes)
    {
        if(training.getId() != null){
            redirectAttributes.addFlashAttribute("success", "Formation modifié");
        } else {
            redirectAttributes.addFlashAttribute("success", "Formation ajouté");
        }
        try {
            trainingRepository.save(training);
        } catch (Exception e){
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenu");
        }

        return "redirect:";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
    {
        Optional<Training> training = trainingRepository.findById(Long.valueOf(id));
        training.ifPresent(value -> trainingRepository.delete(value));

        redirectAttributes.addFlashAttribute("success", "Une formation a été supprimé");

        return "redirect:/";
    }


}
