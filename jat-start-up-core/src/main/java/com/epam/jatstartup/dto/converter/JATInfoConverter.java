package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.JATInfoDTO;
import com.epam.jatstartup.entity.JAT;
import com.epam.jatstartup.entity.participant.ParticipationInfo;
import com.epam.jatstartup.entity.participant.Role;
import com.epam.jatstartup.infrastructure.DTOConverter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@DTOConverter
@RequiredArgsConstructor
public class JATInfoConverter {

    private final ParticipantToDTOConverter participantConverter;
    private final ScheduleToDTOConverter scheduleConverter;

    public JATInfoDTO toDTO(JAT jat) {
        LocalDate now = LocalDate.now();
        LocalDate start = now.minusMonths(1);
        LocalDate end = now.plusMonths(1);
        return JATInfoDTO.builder()
                .location(jat.getCountryCode())
                .language(jat.getLanguageCode())
                .threadCount(jat.getThreads().size())
                .simpleScheduleInfo(scheduleConverter.toDTO(jat.getThreads(), start, end))
                .experts(jat.getParticipationInfos().stream()
                        .filter(this::isExpert)
                        .map(ParticipationInfo::getUser)
                        .map(participantConverter::toDTO)
                        .toList())
                .mentees(jat.getParticipationInfos().stream()
                        .filter(this::isNotExpert)
                        .map(ParticipationInfo::getUser)
                        .map(participantConverter::toDTO)
                        .toList())
                .build();
    }

    private boolean isExpert(ParticipationInfo participation) {
        return participation.getRoles().stream()
                .map(Role::getName)
                .filter(Objects::nonNull)
                .anyMatch(roleName -> roleName.contains("EXPERT"));
    }

    private boolean isNotExpert(ParticipationInfo participation) {
        return !isExpert(participation);
    }

}
