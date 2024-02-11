package com.market.internet_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
 public class InternetShopApplication {
	   public static void main(String[] args) {
		     ConfigurableApplicationContext context = SpringApplication.run(InternetShopApplication.class, args);
		     PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
		     System.out.println("password:" + encoder.encode("admin"));
		   }
	 }

