package com.example.demo.model;

import jakarta.persistence.*; // Updated to jakarta.persistence for Spring Boot 3
import java.time.LocalDateTime;

@Entity
@Table(name = "COLLABORATORS")
public class Collaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collaborator", length = 20)
    private Long idCollaborator;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(name = "hiring_date", nullable = false)
    private LocalDateTime hiringDate;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "notes", length = 250)
    private String notes;

    // Getters and Setters

    public Long getIdCollaborator() {
        return idCollaborator;
    }

    public void setIdCollaborator(Long idCollaborator) {
        this.idCollaborator = idCollaborator;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
