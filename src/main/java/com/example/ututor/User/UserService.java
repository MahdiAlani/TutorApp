package com.example.ututor.User;

import com.example.ututor.Dto.LoginDto;
import com.example.ututor.Dto.RegisterDto;
import com.example.ututor.Role.Role;
import com.example.ututor.Role.RoleRepository;
import com.example.ututor.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    public UserEntity saveUser(RegisterDto registerDto) {

        // User already exists
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return null;
        }

        // Create a new user
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setFullName(registerDto.getFullName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        return userRepository.save(user);
    }

    public String authenticateUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        String token = jwtGenerator.generateToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return token;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}