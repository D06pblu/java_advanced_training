package com.epam.jatstartup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ScheduleDTO {

    private LocalDate start;
    private LocalDate end;
    private List<MeetingSimpleInfoDTO> interviews;

}
