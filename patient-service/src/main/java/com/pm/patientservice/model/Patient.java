package com.pm.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


@Entity
public class Patient {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    @NotNull
    private String name;
    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String address;

    @NotNull
    private String dateOfBirth;


    private String registeredDate;



    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addresse) {
        this.address = addresse;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}


