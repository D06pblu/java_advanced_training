package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.entity.meeting.Interview;
import com.epam.jatstartup.entity.meeting.Meeting;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.service.MenteeLastInterviewResolver;
import com.sun.istack.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class MenteeLastInterviewResolverImpl implements MenteeLastInterviewResolver {

    @Override
    @Nullable
    public LocalDate resolveLastInterviewDate(User mentee) {
        return mentee.getInterviews().stream()
                .map(Interview::getMeeting)
                .filter(meeting -> meeting.getDateTime().isBefore(LocalDateTime.now()))
                .max(Comparator.comparing(Meeting::getDateTime))
                .map(meeting -> meeting.getDateTime().toLocalDate())
                .orElse(null);
    }

}
