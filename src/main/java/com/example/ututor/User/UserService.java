package com.example.ututor.User;

import com.example.ututor.Dto.AuthResponseDto;
import com.example.ututor.Dto.LoginDto;
import com.example.ututor.Dto.RegisterDto;
import com.example.ututor.Dto.UserDto;
import com.example.ututor.Role.Role;
import com.example.ututor.Role.RoleRepository;
import com.example.ututor.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final JWTGenerator jwtGenerator;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDto convertToDto(UserEntity user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getUsername());
        dto.setName(user.getName());
        dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList())); // Convert Role to Strings
        return dto;
    }

    public UserEntity saveUser(RegisterDto registerDto) {

        // User already exists
        if (userRepository.existsByUsername(registerDto.getEmail())) {
            return null;
        }

        System.out.println(registerDto.getEmail());
        System.out.println(registerDto.getPassword());

        // Create a new user
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        return userRepository.save(user);
    }

    public AuthResponseDto authenticateUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        String accessToken = jwtGenerator.generateToken(authentication, "ACCESS");
        String refreshToken = jwtGenerator.generateToken(authentication, "REFRESH");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Getting the user
        Optional<UserEntity> user = userRepository.findByUsername(loginDto.getEmail());
        UserDto userDto = convertToDto(user.get());

        return new AuthResponseDto(accessToken, refreshToken, userDto);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}