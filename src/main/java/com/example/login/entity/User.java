package com.example.login.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(length = 20)
    String username;

    @Column(length = 20)
    String password;

    @Column(length = 50, unique = true)
    String email;
}
