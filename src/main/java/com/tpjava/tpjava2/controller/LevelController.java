package com.tpjava.tpjava2.controller;

import com.tpjava.tpjava2.entity.Level;
import com.tpjava.tpjava2.entity.Students;
import com.tpjava.tpjava2.repository.LevelRepository;
import com.tpjava.tpjava2.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/level")
public class LevelController {

    @Autowired
    LevelRepository levelRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Level> levelList = levelRepository.findAll();
        model.addAttribute("levelList", levelList);
        return "level/levelList";
    }

    @GetMapping("/add")
    public String getForm(Model model)
    {
        Level level = new Level();
        model.addAttribute("level", level);

        return "level/levelForm";
    }

    @GetMapping("/modify/{id}")
    public String updateForm(@PathVariable String id, Model model)
    {
        Optional<Level> level = levelRepository.findById(Long.valueOf(id));
        model.addAttribute("level", level);

        return "level/levelForm";
    }

    @PostMapping("/post")
    public String postForm(@ModelAttribute Level level, Model model, RedirectAttributes redirectAttributes)
    {
        if(level.getId() != null){
            redirectAttributes.addFlashAttribute("success", "Niveau modifié");
        } else {
            redirectAttributes.addFlashAttribute("success", "Niveau ajouté");
        }
        try {
            levelRepository.save(level);
        } catch (Exception e){
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenu");
        }

        return "redirect:";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
    {
        Optional<Level> student = levelRepository.findById(Long.valueOf(id));
        student.ifPresent(value -> levelRepository.delete(value));

        redirectAttributes.addFlashAttribute("success", "Un niveau a été supprimé");

        return "redirect:";
    }

}
