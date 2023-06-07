package com.tpjava.tpjava2.controller;

import com.tpjava.tpjava2.entity.Category;
import com.tpjava.tpjava2.entity.Level;
import com.tpjava.tpjava2.entity.Students;
import com.tpjava.tpjava2.entity.Training;
import com.tpjava.tpjava2.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/training")
@AllArgsConstructor
public class TrainingController {

    TrainingRepository trainingRepository;

    StudentsRepository studentsRepository;

    FormerRepository formerRepository;

    LevelRepository levelRepository;

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
        if(!training.isValid()) return "redirect:/training/add";
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

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model)
    {
        Optional<Training> trainingOptional = trainingRepository.findById(Long.valueOf(id));
        if(trainingOptional.isEmpty()) return "redirect:/";

        model.addAttribute("training", trainingOptional.get());

        return "training/trainingDetails";
    }


    @GetMapping("/{id}/students")
    public String trainingFormStudents(@PathVariable String id, Model model)
    {
        Optional<Training> trainingOptional = trainingRepository.findById(Long.valueOf(id));
        if(trainingOptional.isEmpty()) return "redirect:/";

        List<Students> studentsListInTraining = trainingOptional.get().getStudentsList();
        List<Students> filteredStudents = studentsRepository.findAll();

        filteredStudents.removeIf(studentsListInTraining::contains);

        Training training = trainingOptional.get();

        model.addAttribute("training", training);
        model.addAttribute("students", filteredStudents);
        return "training/trainingFormStudents";
    }

    @PostMapping("/{id}/students")
    public String postTrainingFormStudents(@PathVariable String id, @ModelAttribute Training training)
    {
        trainingRepository.save(training);
        return "redirect:/training/"+ id;
    }

    @GetMapping("/{id}/formers")
    public String trainingFormFormer(@PathVariable String id, Model model)
    {
        Optional<Training> trainingOptional = trainingRepository.findById(Long.valueOf(id));
        if(trainingOptional.isEmpty()) return "redirect:/";
        model.addAttribute("training", trainingOptional.get());
        return "training/trainingFormFormer";
    }



}
