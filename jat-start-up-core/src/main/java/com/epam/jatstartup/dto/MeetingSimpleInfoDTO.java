package com.epam.jatstartup.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MeetingSimpleInfoDTO {

    private String type;
    private int thread;
    private LocalDateTime start;
    private LocalDateTime end;
    private ParticipantDTO interviewee;
    private List<ChosenTopicThemeDTO> subjects;

}
