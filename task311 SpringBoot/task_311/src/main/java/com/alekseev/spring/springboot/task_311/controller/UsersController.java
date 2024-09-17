package com.alekseev.spring.springboot.task_311.controller;

import com.alekseev.spring.springboot.task_311.model.User;
import com.alekseev.spring.springboot.task_311.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "list"})
    public String showAllUsers(HttpServletRequest request, Model model, @ModelAttribute("flashMessage") String flashAttribute) {
        String requestURI = request.getRequestURI();
        model.addAttribute("currentUri", requestURI);
        model.addAttribute("users", userService.getAllUsers());

        return "list";
    }

    @GetMapping(value = "/new")
    public String addUserForm(HttpServletRequest request, Model model, @ModelAttribute("user") User user) {
        String requestURI = request.getRequestURI();
        model.addAttribute("currentUri", requestURI);
        return "form";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(HttpServletRequest request, @PathVariable(value = "id", required = true) long id, Model model,
                               RedirectAttributes attributes) {
        String requestURI = request.getRequestURI();
        model.addAttribute("currentUri", requestURI);
        User user = userService.readUser(id);

        if (null == user) {
            attributes.addFlashAttribute("flashMessage", "User are not exists!");
            return "redirect:/users";
        }

        model.addAttribute("user", userService.readUser(id));
        return "form";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user, BindingResult bindingResult,
                           RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        userService.createOrUpdateUser(user);
        attributes.addFlashAttribute("flashMessage",
                "User " + user.getFirstName() + " successfully created!");
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(HttpServletRequest request, Model model, @RequestParam(value = "id", required = true, defaultValue = "") long id,
                             RedirectAttributes attributes) {
        String requestURI = request.getRequestURI();
        model.addAttribute("currentUri", requestURI);
        User user = userService.deleteUser(id);

        attributes.addFlashAttribute("flashMessage", (null == user) ?
                "User are not exists!" :
                "User " + user.getFirstName() + " successfully deleted!");

        return "redirect:/users";
    }
}