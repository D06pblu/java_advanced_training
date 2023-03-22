package com.epam.jatstartup.repository;

import com.epam.jatstartup.entity.meeting.MeetingSeries;
import org.springframework.data.repository.CrudRepository;

public interface MeetingSeriesRepository extends CrudRepository<MeetingSeries, Long> {
}
