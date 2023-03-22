package com.epam.jatstartup.repository;

import com.epam.jatstartup.entity.meeting.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
