 package com.market.internet_shop.controller;

 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;

 @Controller
 public class MainController
 {
   @RequestMapping({"", "/"})
   public String index() {
     System.out.println("We are on the main page");
     return "index";
   }

   @RequestMapping("/login")
   public String login() {
     System.out.println("We are on the login page");
     return "login";
   }

   @RequestMapping("/login-error")
   public String loginError(Model model) {
     System.out.println("We have failed to login ");
     model.addAttribute("loginError", true);
     return "login";
   }
 }