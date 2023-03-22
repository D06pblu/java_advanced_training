package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.MeetingSimpleInfoDTO;
import com.epam.jatstartup.dto.ScheduleDTO;
import com.epam.jatstartup.entity.ThreadSchedule;
import com.epam.jatstartup.infrastructure.DTOConverter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@DTOConverter
@RequiredArgsConstructor
public class ScheduleToDTOConverter {

    private final MeetingSimpleInfoToDTOConverter meetingConverter;

    public ScheduleDTO toDTO(List<ThreadSchedule> schedules, LocalDate start, LocalDate end) {
        List<MeetingSimpleInfoDTO> meetings = schedules.stream()
                .flatMap(schedule -> schedule.getMeetingSeries().stream())
                .flatMap(series -> series.getMeetings().stream())
                .filter(meeting -> meeting.getDateTime().toLocalDate().isAfter(start))
                .filter(meeting -> meeting.getDateTime().toLocalDate().isBefore(end))
                .map(meetingConverter::toDTO)
                .toList();
        return new ScheduleDTO(start, end, meetings);
    }

}
