package com.tsl.taxiapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BookingForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "can not be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "name must contains only letters")
    @Column(length = 30)
    private String name;

    @NotEmpty(message = "email can not be null")
    @NotBlank(message = "can not be blank")
    @Column(length = 50)
    private String email;

    @NotEmpty(message = "source can not be null")
    @NotBlank(message = "can not be blank")
    @Column(name = "source", length = 300)
    private String from;

    @NotEmpty(message = "destination can not be null")
    @NotBlank(message = "can not be blank")
    @Column(name = "destination", length = 300)
    private String to;

    @NotNull(message = "time can not be null")

    private LocalTime time;

    @NotNull(message = "date can not be null")
    private LocalDate date;

    @NotEmpty(message = "comfort can not be null")
    @Column(length = 20)
    private String comfort;

    @Min(value = 1, message = "adult must be at least 1")
    @Max(value = 4, message = "adult must be at most 4")
    private int adult;

    @Max(value = 3, message = "children must be at most 3")
    private int children;

    @NotEmpty(message = "message can not be null")
    @NotBlank(message = "can not be blank")
    @Column(length = 2000)
    private String message;

    public BookingForm() {
    }
    public BookingForm(int id, String name, String email, String from, String to, LocalTime time, LocalDate date,
            String comfort, int adult, int children, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.from = from;
        this.to = to;
        this.time = time;
        this.date = date;
        this.comfort = comfort;
        this.adult = adult;
        this.children = children;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BookingForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", comfort='" + comfort + '\'' +
                ", adult=" + adult +
                ", children=" + children +
                ", message='" + message + '\'' +
                '}';
    }
}
