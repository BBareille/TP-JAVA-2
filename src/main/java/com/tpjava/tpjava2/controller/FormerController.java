package com.tpjava.tpjava2.controller;

import com.tpjava.tpjava2.entity.Former;
import com.tpjava.tpjava2.entity.Level;
import com.tpjava.tpjava2.repository.FormerRepository;
import com.tpjava.tpjava2.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/formers")
public class FormerController {

    @Autowired
    FormerRepository formerRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Former> formerList = formerRepository.findAll();
        model.addAttribute("formerList", formerList);
        return "former/formerList";
    }

    @GetMapping("/add")
    public String getForm(Model model)
    {
        Former former = new Former();
        model.addAttribute("former", former);

        return "former/formerForm";
    }

    @GetMapping("/modify/{id}")
    public String updateForm(@PathVariable String id, Model model)
    {
        Optional<Former> former = formerRepository.findById(Long.valueOf(id));
        model.addAttribute("former", former);

        return "former/formerForm";
    }

    @PostMapping("/post")
    public String postForm(@ModelAttribute Former former, Model model, RedirectAttributes redirectAttributes)
    {
        if(former.getId() != null){
            redirectAttributes.addFlashAttribute("success", "Formateur modifié");
        } else {
            redirectAttributes.addFlashAttribute("success", "Formateur ajouté");
        }
        try {
            formerRepository.save(former);
        } catch (Exception e){
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenu");
        }

        return "redirect:";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
    {
        Optional<Former> student = formerRepository.findById(Long.valueOf(id));
        student.ifPresent(value -> formerRepository.delete(value));

        redirectAttributes.addFlashAttribute("success", "Un formateur a été supprimé");

        return "redirect:";
    }

}
