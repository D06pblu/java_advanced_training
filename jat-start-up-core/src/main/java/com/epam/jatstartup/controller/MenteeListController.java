package com.epam.jatstartup.controller;

import com.epam.jatstartup.dto.SimpleMenteeDTO;
import com.epam.jatstartup.service.MenteeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/mentees")
@RequiredArgsConstructor
public class MenteeListController {

    private final MenteeListService menteeListService;

    @GetMapping
    public ResponseEntity<List<SimpleMenteeDTO>> findAll() {
        return ResponseEntity.ok(menteeListService.findAll());
    }
}
