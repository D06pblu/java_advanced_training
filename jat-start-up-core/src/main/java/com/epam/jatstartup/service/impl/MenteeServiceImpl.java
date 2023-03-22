package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.repository.UserRepository;
import com.epam.jatstartup.service.MenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenteeServiceImpl implements MenteeService {

    private final UserRepository userRepository;

}
