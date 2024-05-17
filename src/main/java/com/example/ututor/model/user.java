package com.example.ututor.model;

import javax.persistence;
import lombok.data;

@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String school;
}