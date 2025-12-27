package com.example.birthdayreminder.controller;

import com.example.birthdayreminder.dto.BirthdayResponse;
import com.example.birthdayreminder.dto.CreateBirthdayRequest;
import com.example.birthdayreminder.service.BirthdayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/birthdays")
@RequiredArgsConstructor
public class BirthdayController {

    private final BirthdayService birthdayService;

    @PostMapping("/add")
    public ResponseEntity<BirthdayResponse> addBirthday(
            @RequestBody CreateBirthdayRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName();
        BirthdayResponse createdBirthday = birthdayService.addBirthday(request, email);
        return ResponseEntity.ok(createdBirthday);
    }

    @GetMapping
    public ResponseEntity<List<BirthdayResponse>> getBirthdays(
            Authentication authentication
    ) {
        String email = authentication.getName();
        return ResponseEntity.ok(
                birthdayService.getUserBirthdays(email)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBirthday(
            @PathVariable String id,
            Authentication authentication
    ) {
        String email = authentication.getName();
        birthdayService.deleteBirthday(id, email);
        return ResponseEntity.ok("Birthday deleted successfully");
    }
}
