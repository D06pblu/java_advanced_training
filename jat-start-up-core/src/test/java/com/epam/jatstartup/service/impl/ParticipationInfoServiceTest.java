package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.entity.participant.ParticipationInfo;
import com.epam.jatstartup.entity.participant.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParticipationInfoServiceTest {

    @InjectMocks
    ParticipationInfoService participationInfoService;

    @Test
    void given_userIsNull_when_resolveLastParticipation_then_throwIllegalArgumentException() {
        //given
        User user = null;
        //when
        Executable executable = () -> participationInfoService.resolveLastParticipation(user);
        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("User should not be null", exception.getMessage());
    }

    @Test
    void given_userHaveNoParticipants_when_resolveLastParticipation_then_throwIllegalStateException() {
        //given
        User user = new User();
        //when
        Executable executable = () -> participationInfoService.resolveLastParticipation(user);
        //then
        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);
        assertEquals("User has no information about participation", exception.getMessage());
    }

    @Test
    void given_userIsValidWithParticipation_when_resolveLastParticipation_then_foundCorrectLastParticipation() {
        //given
        ParticipationInfo info1 = ParticipationInfo.builder()
                .participationStart(LocalDate.of(2023, 1, 1))
                .participationEnd(LocalDate.of(2023, 2, 28))
                .build();
        ParticipationInfo info2 = ParticipationInfo.builder()
                .participationStart(LocalDate.of(2023, 3, 1))
                .build();

        User user = User.builder()
                .participationInfos(Arrays.asList(info1, info2))
                .build();
        //when
        ParticipationInfo lastInfo = participationInfoService.resolveLastParticipation(user);
        //then
        assertEquals(info2, lastInfo);
    }

    @Test
    void given_userIsNull_when_callIsActive_then_throwIllegalArgumentException() {
        //given
        User user = null;
        //when
        Executable executable = () -> participationInfoService.isActive(user);
        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("User should not be null", exception.getMessage());
    }

    @Test
    void given_validUserWithoutParticipation_when_callIsActive_then_throwIllegalStateException() {
        //given
        User user = new User();
        //when
        Executable executable = () -> participationInfoService.isActive(user);
        //then
        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);
        assertEquals("User has no information about participation", exception.getMessage());
    }

    @Test
    void given_validUserWithoutValidParticipation_when_callIsActive_then_returnFalse() {
        //given
        User user = User.builder()
                .participationInfos(Arrays.asList(
                        new ParticipationInfo(),
                        new ParticipationInfo()
                ))
                .build();
        //when
        Executable executable = () -> participationInfoService.isActive(user);
        //then
        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);
        assertEquals("User has no valid information about participation", exception.getMessage());
    }

    @Test
    void given_validUserWithValidParticipation_when_callIsActive_then_returnFalse() {
        //given
        User user = User.builder()
                .participationInfos(Arrays.asList(
                        ParticipationInfo.builder()
                                .participationStart(LocalDate.of(2022,3,1))
                                .participationEnd(LocalDate.of(2022, 6, 25))
                                .build(),
                        ParticipationInfo.builder()
                                .participationStart(LocalDate.of(2022,7,11))
                                .build()
                ))
                .build();
        //when
        boolean active = participationInfoService.isActive(user);
        //then
        assertTrue(active);
    }

}