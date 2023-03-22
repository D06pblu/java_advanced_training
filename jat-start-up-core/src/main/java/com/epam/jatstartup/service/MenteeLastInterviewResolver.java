package com.epam.jatstartup.service;

import com.epam.jatstartup.entity.participant.User;

import java.time.LocalDate;

public interface MenteeLastInterviewResolver {

    LocalDate resolveLastInterviewDate(User mentee);

}
