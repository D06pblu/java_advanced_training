package com.epam.jatstartup.controller;

import com.epam.jatstartup.dto.JATInfoDTO;
import com.epam.jatstartup.service.JATService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jat")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
@Log4j2
public class MainPageController {

    private final JATService jatService;

    @GetMapping("/{jatNumber}")
    public ResponseEntity<JATInfoDTO> getGeneralInfo(@PathVariable int jatNumber) {
        JATInfoDTO jatDTO = jatService.getGeneralInfo(jatNumber);
        log.info(jatDTO.toString());
        if (jatDTO.getLocation() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(jatDTO);
    }

}
