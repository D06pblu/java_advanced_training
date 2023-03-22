package com.epam.jatstartup.service;

import com.epam.jatstartup.entity.participant.ParticipationInfo;
import com.epam.jatstartup.entity.participant.User;

public interface ParticipationResolver {

    ParticipationInfo resolveLastParticipation(User participant);
}
