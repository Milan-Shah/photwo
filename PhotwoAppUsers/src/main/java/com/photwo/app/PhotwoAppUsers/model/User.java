package com.photwo.app.PhotwoAppUsers.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    @Size(min = 2, message = "First name must be greater than 2 characters")
    public String firstName;

    @Column(nullable = false)
    @Size(min = 2, message = "Last name must be greater than 2 characters")
    public String lastName;

    @Column
    public String userId;

    @Column(nullable = false, unique = true)
    @Email
    public String email;

    @Column(nullable = false)
    @Size(min = 8, message = "Password must be 8 characters.")
    public String password;

    protected User() {}

    public User(Long id, @Size(min = 2, message = "First name must be greater than 2 characters")
            String firstName, @Size(min = 2, message = "Last name must be greater than 2 characters")
            String lastName, String userId, @Email String email, @Size(min = 8, message = "Password must be 8 characters.")
            String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.email = email;
        this.password = password;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
