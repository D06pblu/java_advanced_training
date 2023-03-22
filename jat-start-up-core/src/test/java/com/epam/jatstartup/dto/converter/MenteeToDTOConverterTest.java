package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.SimpleMenteeDTO;
import com.epam.jatstartup.entity.participant.LeaveReason;
import com.epam.jatstartup.entity.participant.ParticipationInfo;
import com.epam.jatstartup.entity.participant.Role;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.service.ActivityResolver;
import com.epam.jatstartup.service.FeedbackService;
import com.epam.jatstartup.service.ParticipationResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenteeToDTOConverterTest {

    @InjectMocks
    private SimpleMenteeToDTOConverter converter;

    @Mock
    private ParticipationResolver participationResolver;
    @Mock
    private FeedbackService feedbackService;
    @Mock
    private ActivityResolver activityResolver;

    @Test
    void when_validUser_then_fullDataAreConverted() {
        //given
        LeaveReason lReason = new LeaveReason();
        lReason.setWording("Improved skills");

        Role role = new Role();
        role.setName("MENTEE_HEAD");

        ParticipationInfo participationInfo1 = ParticipationInfo.builder()
                .id(90L)
                .participationStart(LocalDate.now())
                .participationEnd(LocalDate.now())
                .leaveReasons(List.of(lReason))
                .roles(List.of(role))
                .build();

        User user = User.builder()
                .id(101L)
                .name("TasteName")
                .surname("TasteSurname")
                .email("test@email.com")
                .countryCode("EN")
                .notesLink("http://onenote.com")
                .build();

        SimpleMenteeDTO expectedSimpleMenteeDTO = SimpleMenteeDTO.builder()
                .email("test@email.com")
                .fullName("TasteName TasteSurname")
                .location("EN")
                .participationStart(LocalDate.now())
                .participationEnd(LocalDate.now())
                .leaveReason("Improved skills")
                .averageMark(7.5)
                .active(true)
                .head(true)
                .build();

        //when
        when(participationResolver.resolveLastParticipation(user)).thenReturn(participationInfo1);
        when(feedbackService.getAverageMark(user)).thenReturn(7.5);
        when(activityResolver.isActive(user)).thenReturn(true);

        //then
        assertEquals(expectedSimpleMenteeDTO.getFullName(), converter.toDTO(user).getFullName());
    }

    @Test
    void when_userIsNull_then_throwIllegalArgumentExceptionWithMessageUserShouldNotBeNull() {
        //given
        User user = null;

        //when
        Executable executable = () -> converter.toDTO(user);

        //then
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, executable);
        assertEquals("User should not be null", exception.getMessage());
    }

}