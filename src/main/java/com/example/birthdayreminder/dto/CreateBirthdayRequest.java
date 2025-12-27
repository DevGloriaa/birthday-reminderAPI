package com.example.birthdayreminder.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBirthdayRequest {
    private String name;
    private LocalDate date;
    private String note;
}
