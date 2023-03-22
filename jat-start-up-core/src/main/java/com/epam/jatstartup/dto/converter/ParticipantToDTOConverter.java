package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.ParticipantDTO;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.infrastructure.DTOConverter;

@DTOConverter
public class ParticipantToDTOConverter {

    public ParticipantDTO toDTO(User user) {
        String fullName = user.getName() + " " + user.getSurname();
        String email = user.getEmail();
        boolean isHead = user.getParticipationInfos().stream()
                .flatMap(participationInfo -> participationInfo.getRoles().stream())
                .anyMatch(role -> role.getName().contains("HEAD"));
        return new ParticipantDTO(fullName, email, isHead);
    }

}
