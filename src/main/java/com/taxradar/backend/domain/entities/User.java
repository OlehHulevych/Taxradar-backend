package com.taxradar.backend.domain.entities;

import com.taxradar.backend.domain.common.BaseEntity;
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User extends BaseEntity {


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true, length = 8)
    private String ico;

    protected User() {}

    public User(String firstName, String lastName, String phone, String email, LocalDate birthdate, String ico) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.ico = ico;
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getIco() {
        return ico;
    }
}
