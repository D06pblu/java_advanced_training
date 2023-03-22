package com.epam.jatstartup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipantDTO {

    private String fullName;
    private String email;
    private boolean head;

}
