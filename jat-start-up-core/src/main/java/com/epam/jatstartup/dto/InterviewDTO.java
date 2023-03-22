package com.epam.jatstartup.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class InterviewDTO {

    private Integer thread;
    private ParticipantDTO interviewee;
    private LocalDateTime dateTime;
    private String type;
    private Double averageMark;
    private List<ChosenTopicThemeDTO> subjects;

}
