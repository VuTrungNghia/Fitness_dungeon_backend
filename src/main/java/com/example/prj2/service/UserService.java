package com.example.prj2.service;

import com.example.prj2.config.SecurityConfig;
import com.example.prj2.dto.user.UserRequestDTO;
import com.example.prj2.dto.user.UserResponseDTO;
import com.example.prj2.dto.user.UserUpdateDTO;
import com.example.prj2.entity.User;
import com.example.prj2.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.beans.PropertyDescriptor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private SecurityConfig securityConfig;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAllByDeletedAtIsNull();
    }

    public UserResponseDTO addUser(UserRequestDTO userdto) {
        if (!userdto.getConfirmPassword().equals(userdto.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu không chính xác");
        }
        if (userRepository.existsByEmail(userdto.getEmail())) {
            throw new RuntimeException("Email đã tồn tại!");
        }
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setEmail(userdto.getEmail());
        user.setPassword(securityConfig.passwordEncoder().encode(userdto.getPassword()));
        user.setRole(userdto.getRole());
        User savedUser = userRepository.save(user);
        return new UserResponseDTO(
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    public UserResponseDTO updateUser(@RequestBody UserUpdateDTO user_update, Long user_id) {
        User user = userRepository.findById(Math.toIntExact(user_id))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user_id));

        BeanUtils.copyProperties(user_update, user, getNullPropertyNames(user_update));
        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole());
    }

    public User deleteUser(Long user_id, Boolean is_delete) {
        User user = userRepository.findById(Math.toIntExact(user_id))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user_id));
        if(is_delete) {
            user.setDeleteAt(LocalDateTime.now());
            userRepository.save(user);
        };
        return user;
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        return emptyNames.toArray(new String[0]);
    }
}
