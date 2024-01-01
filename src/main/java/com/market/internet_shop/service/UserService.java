package com.market.internet_shop.service;

import com.market.internet_shop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {//for security
    boolean save(UserDTO userDTO);
}
