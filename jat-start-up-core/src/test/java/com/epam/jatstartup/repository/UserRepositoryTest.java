package com.epam.jatstartup.repository;

import com.epam.jatstartup.entity.participant.ParticipationInfo;
import com.epam.jatstartup.entity.participant.Role;
import com.epam.jatstartup.entity.participant.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepositoryTest {

    private static final String MENTEE = "MENTEE";
    private static final String EXPERT = "EXPERT";
    private List<User> mentees;
    private List<User> experts;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ParticipationInfoRepository participationInfoRepository;

    @BeforeEach
    void setUp() {
        Role menteeRole = saveAndGetRole(MENTEE);
        Role expertRole = saveAndGetRole(EXPERT);

        User firstUser = saveAndGetUser();
        User secondUser = saveAndGetUser();

        saveParticipationInfo(menteeRole, firstUser);
        saveParticipationInfo(expertRole, firstUser);
        saveParticipationInfo(menteeRole, secondUser);
        saveParticipationInfo(menteeRole, secondUser);

        mentees = List.of(firstUser, secondUser);
        experts = List.of(firstUser);
    }

    @Test
    void given_menteeRoleName_when_isValid_then_returnListOfMentees() {

        //given
        List<Long> expectedMenteeIds = mentees.stream().map(User::getId).toList();
        //when
        Set<User> foundMentees = userRepository.findAllByParticipationInfosRolesName(MENTEE);
        List<Long> actualMenteeIds = foundMentees.stream().map(User::getId).toList();
        //then
        assertEquals(expectedMenteeIds, actualMenteeIds);
    }

    @Test
    void given_expertRoleName_when_isValid_then_returnListOfExperts() {

        //given
        List<Long> expectedExpertIds = experts.stream().map(User::getId).toList();
        //when
        Set<User> foundExperts = userRepository.findAllByParticipationInfosRolesName(EXPERT);
        List<Long> actualExpertIds = foundExperts.stream().map(User::getId).toList();
        //then
        assertEquals(expectedExpertIds, actualExpertIds);
    }

    @Test
    void given_roleName_when_isNotValid_then_returnEmptyList() {

        //given
        List<Long> expectedMenteeIds = new ArrayList<>();
        //when
        Set<User> foundCats = userRepository.findAllByParticipationInfosRolesName("cat");
        List<Long> actualMenteeIds = foundCats.stream().map(User::getId).toList();
        //then
        assertEquals(expectedMenteeIds, actualMenteeIds);
    }

    private Role saveAndGetRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    private User saveAndGetUser() {
        User user = new User();
        return userRepository.save(user);
    }

    private void saveParticipationInfo(Role role, User user) {
        ParticipationInfo participationInfo = ParticipationInfo.builder()
                .roles(List.of(role))
                .user(user)
                .build();
        participationInfoRepository.save(participationInfo);
    }

}
