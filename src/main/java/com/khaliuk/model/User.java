package com.khaliuk.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TOKEN")
    private String token;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @ManyToMany(/*cascade = {CascadeType.MERGE, CascadeType.PERSIST}*/)
    @JoinTable(name = "USERS_TO_ROLES",
            joinColumns = @JoinColumn(name = "FK_USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "FK_ROLE_ID", referencedColumnName = "ID"))
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(Long id, String username, String password,
                String email,
                String token, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public enum UserStatus {
        PENDING_VERIFICATION,
        ACTIVE,
        BLOCKED
    }
}
