package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.InterviewDTO;
import com.epam.jatstartup.entity.learn.Feedback;
import com.epam.jatstartup.entity.meeting.Interview;
import com.epam.jatstartup.infrastructure.DTOConverter;
import lombok.RequiredArgsConstructor;

@DTOConverter
@RequiredArgsConstructor
public class InterviewToDTOConverter {

    private final ParticipantToDTOConverter participantConverter;
    private final ChosenThemesToDTOConverter topicThemeConverter;

    public InterviewDTO toDTO(Interview interview) {
        return InterviewDTO.builder()
                .thread(interview.getMeeting().getSeries().getSchedule().getId())
                .interviewee(participantConverter.toDTO(interview.getInterviewee()))
                .dateTime(interview.getMeeting().getDateTime())
                .type(interview.getType().getName())
                .averageMark(interview.getFeedbacks().stream()
                        .filter(feedback -> feedback.getMark() > 0)
                        .mapToDouble(Feedback::getMark)
                        .average()
                        .orElse(0))
                .subjects(topicThemeConverter.toDTO(interview.getThemes()))
                .build();
    }

}
