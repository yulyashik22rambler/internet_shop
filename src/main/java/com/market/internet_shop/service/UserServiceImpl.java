package com.market.internet_shop.service;

import com.market.internet_shop.dao.UserRepository;
import com.market.internet_shop.domain.Role;
import com.market.internet_shop.domain.User;
import com.market.internet_shop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchedPassword())) {
            throw new RuntimeException("Password is not equals");
        }
        User user = User.builder().name(userDTO.getName()).password(passwordEncoder.encode(userDTO.getPassword())).email(userDTO.getEmail()).role(Role.CLIENT).build();
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User userByFirstName = userRepository.findFirstByName(userName);
        if (userByFirstName == null) {
            throw new UsernameNotFoundException("User was not found  with name " + userName);
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(userByFirstName.getRole().name()));
        return new org.springframework.security.core.userdetails.User(userByFirstName.getName(),
                userByFirstName.getPassword(), roles);
    }
}
