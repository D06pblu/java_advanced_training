package com.epam.jatstartup.controller;

import com.epam.jatstartup.entity.JAT;
import com.epam.jatstartup.entity.meeting.Interview;
import com.epam.jatstartup.entity.meeting.MeetingSeries;
import com.epam.jatstartup.entity.meeting.QA;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.repository.InterviewRepository;
import com.epam.jatstartup.repository.JATRepository;
import com.epam.jatstartup.repository.MeetingSeriesRepository;
import com.epam.jatstartup.repository.QARepository;
import com.epam.jatstartup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@SuppressWarnings("java:S4684")
public class TestController {

    private final UserRepository userRepository;
    private final JATRepository jatRepository;
    private final MeetingSeriesRepository meetingSeriesRepository;
    private final QARepository qaRepository;
    private final InterviewRepository interviewRepository;

    @PostMapping(path = "/test/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postTest(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok("done " + savedUser.getId());
    }

    @PostMapping(path = "/test/jat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postJAT(@RequestBody JAT jat) {
        JAT savedJAT = jatRepository.save(jat);
        return ResponseEntity.ok("JAT saved with id=" + savedJAT.getId());
    }

    @PostMapping(path = "/test/meeting/series", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postMeetingSeries(@RequestBody MeetingSeries meetingSeries) {
        MeetingSeries savedSeries = meetingSeriesRepository.save(meetingSeries);
        return ResponseEntity.ok("MeetingSeries saved with id=" + savedSeries.getId());
    }

    @PostMapping(path = "/test/qa", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postQA(@RequestBody QA qa) {
        QA savedQA = qaRepository.save(qa);
        return ResponseEntity.ok("QA saved with id=" + savedQA.getId());
    }

    @PostMapping(path = "/test/interview", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postInterview(@RequestBody Interview interview) {
        Interview savedInterview = interviewRepository.save(interview);
        return ResponseEntity.ok("Interview saved with id=" + savedInterview.getId());
    }

}
