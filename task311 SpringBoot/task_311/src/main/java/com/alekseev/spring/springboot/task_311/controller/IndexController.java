package com.alekseev.spring.springboot.task_311.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String indexView(HttpServletRequest request, Model model) {
        String requestURI = request.getRequestURI();
        model.addAttribute("currentUri", requestURI);
        return "index";
    }

}
