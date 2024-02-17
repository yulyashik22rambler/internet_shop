package com.market.internet_shop.service;

import com.market.internet_shop.domain.User;
import com.market.internet_shop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {//for security
  boolean save(UserDTO paramUserDTO);
  void save(User user);
    List<UserDTO> getAll();

  User findByName(String name);

  void updateProfile(UserDTO userDTO);
}
