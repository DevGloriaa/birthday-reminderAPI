package com.example.birthdayreminder.model;

import com.example.birthdayreminder.enums.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "birthdays")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Birthday {

    @Id
    private String id;

    private String name;
    private LocalDate date;
    private String note;

    private String userEmail;

    private Category category;
}
