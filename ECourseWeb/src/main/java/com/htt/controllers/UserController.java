/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Teacher;
import com.htt.pojo.User;
import com.htt.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Admin
 */
@Controller
public class UserController {

    @Autowired
    private UserService userSer;

    @GetMapping("/users")
    public String viewUser(Model model) {
        model.addAttribute("users", this.userSer.getUsers());

        return "users";
    }

    @GetMapping("/user")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @GetMapping("/users/{userId}")
    public String userUpdate(Model model, @PathVariable(value = "userId") Long id) {
        model.addAttribute("user", this.userSer.getUserById(id));
        return "user";
    }

    @PostMapping("/users")
    public String submitForm(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user";
        }
        this.userSer.addOrUpdate(user);
        return "redirect:/";
    }

}
