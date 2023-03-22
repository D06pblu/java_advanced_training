package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.entity.learn.Feedback;
import com.epam.jatstartup.entity.meeting.Interview;
import com.epam.jatstartup.entity.participant.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceImplTest {

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @Test
    void when_userWithFeedbacks_then_calculateAverageMark() {
        //given
        Feedback feedback1 = new Feedback();
        feedback1.setMark(7.5);

        Feedback feedback2 = new Feedback();
        feedback2.setMark(2);

        Feedback feedback3 = new Feedback();
        feedback3.setMark(10);

        Interview interview1 = new Interview();
        interview1.setFeedbacks(List.of(feedback1, feedback2));

        Interview interview2 = new Interview();
        interview2.setFeedbacks(List.of(feedback3));

        User user = User.builder()
                .interviews(List.of(interview1, interview2))
                .build();

        double expectedAverageMark = 6.5;
        //when
        double actualAverageMark = feedbackService.getAverageMark(user);
        //then
        assertEquals(expectedAverageMark, actualAverageMark, 0.1);
    }

    @Test
    void when_userWithoutFeedbacks_then_returnZero() {
        //given
        Interview interview1 = new Interview();
        Interview interview2 = new Interview();
        User user = User.builder()
                .interviews(List.of(interview1, interview2))
                .build();

        double expectedAverageMark = 0;
        //when
        double actualAverageMark = feedbackService.getAverageMark(user);
        //then
        assertEquals(expectedAverageMark, actualAverageMark, 0.1);
    }

    @Test
    void when_userWithEmptyInterview_then_returnZero() {
        //given
        Interview interview1 = null;
        Interview interview2 = null;

        User user = User.builder()
                .interviews(Arrays.asList(interview1, interview2))
                .build();

        double expectedAverageMark = 0;
        //when
        double actualAverageMark = feedbackService.getAverageMark(user);
        //then
        assertEquals(expectedAverageMark, actualAverageMark, 0.1);
    }

    @Test
    void when_userWithoutInterview_then_returnZero() {
        //given
        User user = new User();

        double expectedAverageMark = 0;
        //when
        double actualAverageMark = feedbackService.getAverageMark(user);
        //then
        assertEquals(expectedAverageMark, actualAverageMark, 0.1);
    }

    @Test
    void when_userIsNull_then_throwIllegalArgumentException() {
        //given
        User user = null;

        double expectedAverageMark = 0;
        //when
        Executable executable = () -> feedbackService.getAverageMark(user);
        //then
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Mentee should not be null", illegalArgumentException.getMessage());
    }

}