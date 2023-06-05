package com.tpjava.tpjava2.controller;

import com.tpjava.tpjava2.entity.Former;
import com.tpjava.tpjava2.entity.Level;
import com.tpjava.tpjava2.repository.FormerRepository;
import com.tpjava.tpjava2.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/former")
public class FormerController {

    @Autowired
    FormerRepository formerRepository;

    @GetMapping
    public String index(Model model){
        List<Former> formerList = formerRepository.findAll();
        model.addAttribute("formerList", formerList);
        return "former/formerList";
    }
}
