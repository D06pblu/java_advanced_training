package com.epam.jatstartup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JATInfoDTO {

    private String location;
    private String language;
    private int threadCount;
    private ScheduleDTO simpleScheduleInfo;
    private List<ParticipantDTO> experts;
    private List<ParticipantDTO> mentees;

}
