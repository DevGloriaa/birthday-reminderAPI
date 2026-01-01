package com.example.birthdayreminder.dto;

import com.example.birthdayreminder.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BirthdayResponse {
    private String id;
    private String name;
    private LocalDate date;
    private String note;

    private Category category;
}
