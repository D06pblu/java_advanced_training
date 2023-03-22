package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.service.MenteeLastInterviewResolver;
import com.epam.jatstartup.service.MenteeSilenceDaysService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MenteeSilenceDaysCalculator implements MenteeSilenceDaysService {

    private final ParticipationInfoService participationInfoService;
    private final MenteeLastInterviewResolver menteeLastInterviewResolver;
    @Override
    public int calculateSilenceDays(User mentee) {
        LocalDate lastMeetingDate = menteeLastInterviewResolver.resolveLastInterviewDate(mentee);

        if (lastMeetingDate == null) {
            LocalDate participationStart = participationInfoService
                    .resolveLastParticipation(mentee).getParticipationStart();
            return calculatePassedDays(participationStart);
        }
        return calculatePassedDays(lastMeetingDate);
    }

    @Override
    public int calculatePassedDays(LocalDate lastMeetingDate) {
        return (int) (LocalDate.now().toEpochDay() - lastMeetingDate.toEpochDay());
    }
}
