package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.SimpleMenteeDTO;
import com.epam.jatstartup.entity.participant.LeaveReason;
import com.epam.jatstartup.entity.participant.ParticipationInfo;
import com.epam.jatstartup.entity.participant.Role;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.infrastructure.DTOConverter;
import com.epam.jatstartup.service.ActivityResolver;
import com.epam.jatstartup.service.FeedbackService;
import com.epam.jatstartup.service.ParticipationResolver;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@DTOConverter
@RequiredArgsConstructor
public class SimpleMenteeToDTOConverter {

    private final ParticipationResolver participationResolver;
    private final FeedbackService feedbackService;
    private final ActivityResolver activityResolver;

    public SimpleMenteeDTO toDTO(User mentee) {

        if (mentee == null) {
            throw new IllegalArgumentException("User should not be null");
        }
        ParticipationInfo participation = participationResolver.resolveLastParticipation(mentee);
        return SimpleMenteeDTO.builder()
                .id(mentee.getEmail())
                .email(mentee.getEmail())
                .fullName(mentee.getName() + " " + mentee.getSurname())
                .location(mentee.getCountryCode())
                .participationStart(participation.getParticipationStart())
                .participationEnd((participation.getParticipationEnd()))
                .leaveReason(participation.getLeaveReasons()
                        .stream()
                        .map(LeaveReason::getWording)
                        .collect(Collectors.joining(", ")))
                .averageMark(feedbackService.getAverageMark(mentee))
                .active(activityResolver.isActive(mentee))
                .head(participation.getRoles()
                        .stream()
                        .map(Role::getName)
                        .anyMatch("MENTEE_HEAD"::equals))
                //TODO check later who's faster
//.anyMatch(role -> "MENTEE_HEAD".equals(role.getName()))) //second variant, maybe it's faster
                .build();
    }
}
