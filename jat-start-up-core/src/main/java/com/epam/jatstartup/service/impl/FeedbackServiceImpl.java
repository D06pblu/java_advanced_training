package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.entity.learn.Feedback;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Override
    public double getAverageMark(User mentee) {

        if (mentee == null) {
            throw new IllegalArgumentException("Mentee should not be null");
        }
        if (mentee.getInterviews() == null) {
            return 0;
        }
        return mentee.getInterviews().stream()
                .filter(interview -> interview != null && interview.getFeedbacks() != null)
                .flatMap(interview -> interview.getFeedbacks().stream())
                .mapToDouble(Feedback::getMark)
                .average()
                .orElse(0);
    }
}
