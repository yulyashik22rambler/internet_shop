package com.market.internet_shop.controller;

import com.market.internet_shop.domain.User;
import com.market.internet_shop.dto.UserDTO;
import com.market.internet_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

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

    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal){
        if (principal==null){throw new RuntimeException("You are not authorized!");}

        User user = userService.findByName(principal.getName());
        UserDTO userDTO = UserDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
        model.addAttribute("user",userDTO);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(UserDTO userDTO, Model model, Principal principal){
        if (principal==null|| !Objects.equals(principal.getName(),userDTO.getUsername()))
        {throw new RuntimeException("You are not authorized!");}

        if(userDTO.getPassword()!=null&& !userDTO.getPassword().isEmpty()
                && !Objects.equals(userDTO.getPassword(),userDTO.getMatchingPassword())) {
            model.addAttribute("user", userDTO);
            return "profile";
        }else {
            userService.updateProfile(userDTO);
            return "redirect:/users/profile";
        }

    }
}