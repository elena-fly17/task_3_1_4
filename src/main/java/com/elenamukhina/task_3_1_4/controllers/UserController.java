package com.elenamukhina.task_3_1_4.controllers;

import com.elenamukhina.task_3_1_4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public String showUserPage(Principal principal, Model model) {
        model.addAttribute("user", userRepository.findByUsername(principal.getName()));
        model.addAttribute("user_on_navbar",
                userRepository.findByUsername(principal.getName()));
        return "user";
    }
}
