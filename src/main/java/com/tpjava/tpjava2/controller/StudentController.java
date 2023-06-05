package com.tpjava.tpjava2.controller;

import com.tpjava.tpjava2.entity.Students;
import com.tpjava.tpjava2.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("")
    public String index(Model model){
        List<Students> studentsList = studentsRepository.findAll();
        model.addAttribute("studentsList", studentsList);
        return "students/studentsList";
    }
}
