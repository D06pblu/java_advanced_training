package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.entity.participant.ParticipationInfo;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.service.ActivityResolver;
import com.epam.jatstartup.service.ParticipationResolver;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ParticipationInfoService implements ParticipationResolver, ActivityResolver {

    @Override
    public ParticipationInfo resolveLastParticipation(User participant) {
        if (participant == null) {
            throw new IllegalArgumentException("User should not be null");
        }
        List<ParticipationInfo> participationInfos = participant.getParticipationInfos();
        if (participationInfos == null) {
            throw new IllegalStateException("User has no information about participation");
        }
        return participationInfos.stream()
                .filter(info -> info.getParticipationStart() != null)
                .max(Comparator.comparing(ParticipationInfo::getParticipationStart))
                .orElseThrow(() -> new IllegalStateException("User has no valid information about participation"));
    }

    @Override
    public boolean isActive(User participant) {
        return this.resolveLastParticipation(participant)
                .getParticipationEnd() == null;
    }

}
