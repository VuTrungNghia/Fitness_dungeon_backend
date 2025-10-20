package com.example.prj2.controller;

import com.example.prj2.dto.user.UserRequestDTO;
import com.example.prj2.dto.user.UserResponseDTO;
import com.example.prj2.dto.user.UserUpdateDTO;
import com.example.prj2.entity.User;
import com.example.prj2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {
    final private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user")
    public List<User> getAll(){
        return userService.findAll();
    }

    // API thêm user
    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequest) {
        UserResponseDTO response = userService.addUser(userRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateDTO userUpdate, @PathVariable Long user_id) {
        UserResponseDTO response = userService.updateUser(userUpdate,user_id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long user_id, @RequestParam(defaultValue = "true") boolean softDelete) {
        userService.deleteUser(user_id, softDelete);
        String message = softDelete
                ? "User has been soft deleted."
                : "User deleted false";
        return ResponseEntity.ok(message);
    }
}
