package com.epam.jatstartup.service;

import com.epam.jatstartup.entity.participant.User;

public interface FeedbackService {
    double getAverageMark(User mentee);
}
