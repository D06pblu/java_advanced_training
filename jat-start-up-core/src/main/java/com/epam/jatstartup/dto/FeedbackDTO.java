package com.epam.jatstartup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedbackDTO {

    private String expert;
    private String wording;
    private Double mark;
    private InterviewDTO interview;

}
