package com.example.birthdayreminder.service;

import com.example.birthdayreminder.dto.BirthdayResponse;
import com.example.birthdayreminder.dto.CreateBirthdayRequest;

import java.util.List;

public interface BirthdayService {

    BirthdayResponse addBirthday(CreateBirthdayRequest request, String userEmail);

    List<BirthdayResponse> getUserBirthdays(String userEmail);

    void deleteBirthday(String birthdayId, String userEmail);
}
