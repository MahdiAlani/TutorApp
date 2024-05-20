package com.example.ututor.User;

import com.example.ututor.Listing.Listing;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String school;

    @OneToMany(mappedBy = "user")
    private List<Listing> listings;

    public User() {}

    public User(Long id, String username, String password, String email, String firstName, String lastName, String school) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.school = school;
        this.listings = new ArrayList<>();
    }
}