package com.epam.jatstartup.controller;

import com.epam.jatstartup.dto.FullMenteeDTO;
import com.epam.jatstartup.service.MenteeProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mentees")
@RequiredArgsConstructor
public class ProfileController {

    private final MenteeProfileService menteeProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<FullMenteeDTO> getMenteeInfo(@PathVariable long id) {
        FullMenteeDTO fullMenteeDTO = menteeProfileService.findById(id);
        return ResponseEntity.ok(fullMenteeDTO);
    }

}
