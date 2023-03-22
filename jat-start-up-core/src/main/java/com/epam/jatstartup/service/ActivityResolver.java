package com.epam.jatstartup.service;

import com.epam.jatstartup.entity.participant.User;

public interface ActivityResolver {

    boolean isActive(User participant);
}
