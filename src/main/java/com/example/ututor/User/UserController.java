package com.example.ututor.User;

import com.example.ututor.Dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>("User Already exists.", HttpStatus.BAD_REQUEST);
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
