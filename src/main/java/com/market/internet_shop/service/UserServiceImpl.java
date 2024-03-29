package com.market.internet_shop.service;

import com.market.internet_shop.dao.UserRepository;
import com.market.internet_shop.domain.Role;
import com.market.internet_shop.domain.User;
import com.market.internet_shop.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new RuntimeException("Password is not equals");
        }
        User user = User.builder().name(userDTO.getUsername()).password(this.passwordEncoder.encode(userDTO.getPassword())).email(userDTO.getEmail()).role(Role.CLIENT).build();
        this.userRepository.save(user);
        return true;
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return this.userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public User findByName(String name) {
        return userRepository.findFirstByName(name);
    }

    @Override
    @Transactional
    public void updateProfile(UserDTO userDTO) {
        User savedUser = userRepository.findFirstByName(userDTO.getUsername());
        if (savedUser == null) {
            throw new RuntimeException("User was not found!");
        }
        boolean isChanged = false;
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            isChanged = true;
        }
        if (!Objects.equals(userDTO.getEmail(), savedUser.getEmail())) {
            savedUser.setEmail(userDTO.getEmail());
            isChanged = true;
        }
        if (isChanged) {
            userRepository.save(savedUser);
        }

    }

    private UserDTO toDto(User user) {
        return UserDTO.builder().username(user.getName()).email(user.getEmail()).build();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User userByFirstName = userRepository.findFirstByName(userName);
        if (userByFirstName == null) {
            throw new UsernameNotFoundException("User was not found  with name " + userName);
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(userByFirstName.getRole().name()));
        return new org.springframework.security.core.userdetails.User(userByFirstName.getName(), userByFirstName.getPassword(), roles);
    }
}
