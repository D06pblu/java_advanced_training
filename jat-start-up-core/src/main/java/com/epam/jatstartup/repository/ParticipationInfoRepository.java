package com.epam.jatstartup.repository;

import com.epam.jatstartup.entity.participant.ParticipationInfo;
import org.springframework.data.repository.CrudRepository;

public interface ParticipationInfoRepository extends CrudRepository<ParticipationInfo, Long> {

}