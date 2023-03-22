package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.MeetingSimpleInfoDTO;
import com.epam.jatstartup.entity.learn.Theme;
import com.epam.jatstartup.entity.meeting.Meeting;
import com.epam.jatstartup.infrastructure.DTOConverter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@DTOConverter
@RequiredArgsConstructor
public class MeetingSimpleInfoToDTOConverter {

    private final ParticipantToDTOConverter participantConverter;
    private final ChosenThemesToDTOConverter chosenThemesConverter;

    public MeetingSimpleInfoDTO toDTO(Meeting meeting) {
        MeetingSimpleInfoDTO.MeetingSimpleInfoDTOBuilder builder = MeetingSimpleInfoDTO.builder();
        builder.type(resolveType(meeting));
        builder.thread(meeting.getSeries().getSchedule().getThreadNumber());
        LocalDateTime start = meeting.getDateTime();
        builder.start(start);
        builder.end(start.plusHours(1));
        if (meeting.getInterview() != null) {
            builder.interviewee(
                    participantConverter.toDTO(meeting.getInterview().getInterviewee()));
            List<Theme> themes = meeting.getInterview().getThemes();
            builder.subjects(chosenThemesConverter.toDTO(themes));
        }
        return builder.build();
    }

    private String resolveType(Meeting meeting) {
        if (meeting.getInterview() != null) {
            return meeting.getInterview().getType().getName();
        }
        if (meeting.getQa() != null) {
            return "QA";
        }
        if (meeting.getBrainstorm() != null) {
            return "Brainstorm";
        }
        if (meeting.getScrum() != null) {
            return "Scrum";
        }
        return "-";
    }

}
