package com.example.ututor.Dto;

import com.example.ututor.Role.Role;
import com.example.ututor.User.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private List<String> roles;

    public UserDto() {
    }
}
