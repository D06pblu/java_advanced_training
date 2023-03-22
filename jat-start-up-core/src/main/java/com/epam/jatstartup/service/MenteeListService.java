package com.epam.jatstartup.service;

import com.epam.jatstartup.dto.SimpleMenteeDTO;

import java.util.List;

public interface MenteeListService {
    List<SimpleMenteeDTO> findAll();
}
