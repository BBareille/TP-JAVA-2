package com.tpjava.tpjava2.controller;

import com.tpjava.tpjava2.entity.Level;
import com.tpjava.tpjava2.entity.Students;
import com.tpjava.tpjava2.repository.LevelRepository;
import com.tpjava.tpjava2.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/level")
public class LevelController {

    @Autowired
    LevelRepository levelRepository;

    @GetMapping
    public String index(Model model){
        List<Level> levelList = levelRepository.findAll();
        model.addAttribute("levelList", levelList);
        return "level/levelList";
    }
}
