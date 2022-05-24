package com.elenamukhina.task_3_1_4.controllers;

import com.elenamukhina.task_3_1_4.repository.UserRepository;
import com.elenamukhina.task_3_1_4.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository, UserServiceImpl userServiceImpl) {
        this.userRepository = userRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping()
    public String getMainPage(Principal principal, Model model) {

        model.addAttribute("user", userRepository.findByUsername(principal.getName()));
        model.addAttribute("user_on_navbar",
                userRepository.findByUsername(principal.getName()));

        return "index";
    }
}
