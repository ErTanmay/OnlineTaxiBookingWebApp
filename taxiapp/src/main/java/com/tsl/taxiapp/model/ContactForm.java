package com.tsl.taxiapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "contact_form")
public class ContactForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 to 30 characters")
    @Column(length = 30)
    private String name;
    @Email
    @NotEmpty(message = "Email can not be empty")
    @Size(min = 7, max = 50, message = "Email should be between 7 to 50 characters")
    @Column(length = 50)
    private String email;
    @NotNull(message = "Phone number can not be empty")
    @Min(value = 100000000, message = "Phone number should be 10 digits")
    @Max(value = 9999999999L, message = "Phone number should be 10 digits")
    @Column(length = 10)
    private Long phone;
    @NotEmpty(message = "Message can not be empty")
    @Size(min = 3, max = 500, message = "Message should be between 3 to 500 characters")
    @Column(length = 500)
    private String message;

    public ContactForm() {
    }

    public ContactForm(int id, String name, String email, Long phone, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public ContactForm(String name, String email, Long phone, String message) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", message='" + message + '\'' +
                '}';
    }
}
