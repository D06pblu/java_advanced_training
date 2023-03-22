package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.dto.FullMenteeDTO;
import com.epam.jatstartup.dto.converter.FullMenteeToDTOConverter;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.repository.UserRepository;
import com.epam.jatstartup.service.MenteeProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenteeProfileServiceImpl implements MenteeProfileService {

    private final UserRepository userRepository;
    private final FullMenteeToDTOConverter fullMenteeConverter;

    @Override
    public FullMenteeDTO findById(long id) {
        Optional<User> mentee = userRepository.findById(id);
        return mentee
                .map(fullMenteeConverter::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Mentee was not found"));
    }

}
