package com.example.ebookapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "image")
    private String image;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phonenumber", unique = true)
    private String phoneNumber;
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;
    @OneToMany(mappedBy = "user")
    private Set<BookDetails> bookDetails;
    @OneToMany(mappedBy = "user")
    private Set<Cart> carts;
}
