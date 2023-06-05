package com.tpjava.tpjava2.controller;

import com.tpjava.tpjava2.entity.Category;
import com.tpjava.tpjava2.entity.Level;
import com.tpjava.tpjava2.entity.Students;
import com.tpjava.tpjava2.entity.Training;
import com.tpjava.tpjava2.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Students> studentsList = studentsRepository.findAll();
        model.addAttribute("studentsList", studentsList);
        return "students/studentsList";
    }

    @GetMapping("/add")
    public String getForm(Model model)
    {
        Students student = new Students();
        model.addAttribute("student", student);

        return "students/studentsForm";
    }

    @GetMapping("/modify/{id}")
    public String updateForm(@PathVariable String id, Model model)
    {
        Optional<Students> student = studentsRepository.findById(Long.valueOf(id));
        model.addAttribute("student", student);

        return "students/studentsForm";
    }

    @PostMapping("/post")
    public String postForm(@ModelAttribute Students student, Model model, RedirectAttributes redirectAttributes)
    {
        if(student.getId() != null){
            redirectAttributes.addFlashAttribute("success", "Étudiant modifié");
        } else {
            redirectAttributes.addFlashAttribute("success", "Étudiant ajouté");
        }
        try {
            studentsRepository.save(student);
        } catch (Exception e){
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenu");
        }

        return "redirect:";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
    {
        Optional<Students> student = studentsRepository.findById(Long.valueOf(id));
        student.ifPresent(value -> studentsRepository.delete(value));

        redirectAttributes.addFlashAttribute("success", "Un étudiant a été supprimé");

        return "redirect:";
    }
}
