package com.market.internet_shop.controller;

import com.market.internet_shop.dto.UserDTO;
import com.market.internet_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", this.userService.getAll());
        return "userList";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user";
    }


    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model) {
        if (this.userService.save(userDTO)) {
            return "redirect:/users";
        }
        model.addAttribute("user", userDTO);
        return "user";
    }
}