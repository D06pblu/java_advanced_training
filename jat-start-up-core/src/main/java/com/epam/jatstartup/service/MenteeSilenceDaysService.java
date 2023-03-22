package com.epam.jatstartup.service;

import com.epam.jatstartup.entity.participant.User;

import java.time.LocalDate;

public interface MenteeSilenceDaysService {

    int calculateSilenceDays(User mentee);
    int calculatePassedDays(LocalDate lastMeetingDate);

}
