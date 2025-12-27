package com.example.birthdayreminder.repository;

import com.example.birthdayreminder.model.Birthday;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BirthdayRepository extends MongoRepository<Birthday, String> {

    List<Birthday> findByUserEmail(String userEmail);
}
