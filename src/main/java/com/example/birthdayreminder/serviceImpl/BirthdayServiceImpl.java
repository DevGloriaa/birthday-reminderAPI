package com.example.birthdayreminder.serviceImpl;

import com.example.birthdayreminder.dto.BirthdayResponse;
import com.example.birthdayreminder.dto.CreateBirthdayRequest;
import com.example.birthdayreminder.enums.Category;
import com.example.birthdayreminder.model.Birthday;
import com.example.birthdayreminder.repository.BirthdayRepository;
import com.example.birthdayreminder.service.BirthdayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BirthdayServiceImpl implements BirthdayService {

    private final BirthdayRepository birthdayRepository;

    @Override
    public BirthdayResponse addBirthday(CreateBirthdayRequest request, String userEmail) {

        Birthday birthday = new Birthday();
        birthday.setName(request.getName());
        birthday.setDate(request.getDate());
        birthday.setNote(request.getNote());
        birthday.setUserEmail(userEmail);

        birthday.setCategory(request.getCategory() != null ? request.getCategory() : Category.OTHER);

        Birthday savedBirthday = birthdayRepository.save(birthday);

        return new BirthdayResponse(
                savedBirthday.getId(),
                savedBirthday.getName(),
                savedBirthday.getDate(),
                savedBirthday.getNote(),
                savedBirthday.getCategory()
        );
    }

    @Override
    public List<BirthdayResponse> getUserBirthdays(String userEmail) {

        return birthdayRepository.findByUserEmail(userEmail)
                .stream()
                .map(b -> new BirthdayResponse(
                        b.getId(),
                        b.getName(),
                        b.getDate(),
                        b.getNote(),
                        b.getCategory() // Return category to frontend
                ))
                .toList();
    }

    @Override
    public void deleteBirthday(String birthdayId, String userEmail) {

        Birthday birthday = birthdayRepository.findById(birthdayId)
                .orElseThrow(() -> new RuntimeException("Birthday not found"));

        if (!birthday.getUserEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized");
        }

        birthdayRepository.delete(birthday);
    }
}