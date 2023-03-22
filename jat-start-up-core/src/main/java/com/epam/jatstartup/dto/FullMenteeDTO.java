package com.epam.jatstartup.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FullMenteeDTO {

    private SimpleMenteeDTO generalInfo;
    /**
     * Count days without an interview
     */
    private int silenceDays;
    private String notesLink;
    private List<GapDTO> gaps;
    private List<InterviewDTO> pastInterviews;
    private List<InterviewDTO> currentInterviews;

}
