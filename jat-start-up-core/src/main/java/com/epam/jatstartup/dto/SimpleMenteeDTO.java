package com.epam.jatstartup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMenteeDTO {

    private String id;
    private String email;
    private String fullName;
    private String location;
    private LocalDate participationStart;
    private LocalDate participationEnd;
    private String leaveReason;
    private double averageMark;
    private boolean active;
    private boolean head;

}
