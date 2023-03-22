package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.dto.SimpleMenteeDTO;
import com.epam.jatstartup.dto.converter.SimpleMenteeToDTOConverter;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.repository.UserRepository;
import com.epam.jatstartup.service.MenteeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MenteeListServiceImpl implements MenteeListService {

    private final SimpleMenteeToDTOConverter menteeToDTOConverter;
    private final UserRepository userRepository;

    public List<SimpleMenteeDTO> findAll() {
        Set<User> mentees = userRepository.findAllByParticipationInfosRolesName("MENTEE");
        return mentees == null ? new ArrayList<>() : mentees.stream()
                .map(menteeToDTOConverter::toDTO)
                .toList();
    }

}
