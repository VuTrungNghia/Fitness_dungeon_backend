package com.example.prj2.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // Tự động sinh getter/setter/toString()
@NoArgsConstructor      // Constructor rỗng
@AllArgsConstructor     // Constructor đầy đủ

public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String role;
}
