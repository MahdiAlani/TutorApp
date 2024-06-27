package com.example.ututor.User;

import com.example.ututor.Dto.AuthResponseDto;
import com.example.ututor.Dto.LoginDto;
import com.example.ututor.Dto.RegisterDto;
import com.example.ututor.Dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable Long id) { // @PathVariable extracts id from the url
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody RegisterDto registerDto) {

        UserEntity user = userService.saveUser(registerDto);

        // User Created
        if (user != null) {
            return new ResponseEntity<>("User Created", HttpStatus.OK);
        }
        // User already Exists
        return new ResponseEntity<>("User already exists. Please sign in.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        try {
            return new ResponseEntity<>(userService.authenticateUser(loginDto), HttpStatus.OK);

        } catch (AuthenticationException ex) {
            if (ex instanceof BadCredentialsException) {
                return new ResponseEntity<>(new AuthResponseDto("Email or password is incorrect.", null,null),
                        HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(new AuthResponseDto("An error occurred during authentication. Please Try again", null, null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody RegisterDto registerDto) {
        return null;
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
