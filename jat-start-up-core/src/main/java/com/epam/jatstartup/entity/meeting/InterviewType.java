package com.epam.jatstartup.entity.meeting;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InterviewType {
    FIRST_REVIEW("First review"),
    WITH_EXPERTS("Interview with experts"),
    P2P("P2P");

    private final String name;

}