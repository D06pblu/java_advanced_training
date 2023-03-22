package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.dto.SimpleMenteeDTO;
import com.epam.jatstartup.dto.converter.SimpleMenteeToDTOConverter;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenteeListServiceImplTest {

    @InjectMocks
    private MenteeListServiceImpl menteeListService;

    @Mock
    private SimpleMenteeToDTOConverter menteeToDTOConverter;

    @Mock
    private UserRepository userRepository;

    @Test
    void given_menteeData_when_invokedFindAll_then_returnListOfConvertedDTOs() {
        //given
        User mentee = User.builder()
                .id(1L)
                .name("Sergey")
                .surname("Razuev")
                .build();
        SimpleMenteeDTO expectedSimpleMenteeDTO = SimpleMenteeDTO.builder()
                .fullName("Sergey Razuev")
                .build();
        //when
        when(userRepository.findAllByParticipationInfosRolesName(any()))
                .thenReturn(Set.of(mentee));
        when(menteeToDTOConverter.toDTO(any()))
                .thenReturn(expectedSimpleMenteeDTO);
        //then
        assertEquals(List.of(expectedSimpleMenteeDTO.getFullName()),
                menteeListService.findAll().stream()
                        .map(SimpleMenteeDTO::getFullName)
                        .toList());
    }

    @Test
    void given_listOfMenteesIsNull_when_invokedFindAll_then_returnEmptyList() {
        //given
        Set<User> users = null;
        //when
        when(userRepository.findAllByParticipationInfosRolesName(any()))
                .thenReturn(users);
        //then
        assertEquals(new ArrayList<>(), menteeListService.findAll());
    }

}