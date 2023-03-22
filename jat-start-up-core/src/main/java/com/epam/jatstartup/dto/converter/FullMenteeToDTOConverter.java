package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.FullMenteeDTO;
import com.epam.jatstartup.dto.InterviewDTO;
import com.epam.jatstartup.dto.SimpleMenteeDTO;
import com.epam.jatstartup.entity.meeting.Interview;
import com.epam.jatstartup.entity.participant.User;
import com.epam.jatstartup.infrastructure.DTOConverter;
import com.epam.jatstartup.service.MenteeSilenceDaysService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@DTOConverter
@RequiredArgsConstructor
public class FullMenteeToDTOConverter {

    private final SimpleMenteeToDTOConverter simpleConverter; //TODO ох как тут их много, мб надо рефакторить
    private final GapToDTOConverter gapConverter;
    private final InterviewToDTOConverter interviewConverter;
    private final MenteeSilenceDaysService menteeSilenceDaysService;


    public FullMenteeDTO toDTO(User mentee) {
        SimpleMenteeDTO simpleMenteeDTO = simpleConverter.toDTO(mentee);

        return FullMenteeDTO.builder()
                .generalInfo(simpleMenteeDTO)
                .silenceDays(menteeSilenceDaysService.calculateSilenceDays(mentee))
                .notesLink(mentee.getNotesLink())
                .gaps(mentee.getInterviews().stream()
                        .flatMap(interview -> interview.getGaps().stream())
                        .map(gapConverter::convert)
                        .toList())
                .pastInterviews(getInterviewsByPredicate(
                        mentee,
                        interview -> interview.getMeeting().getDateTime().isBefore(LocalDateTime.now())))
                .currentInterviews(getInterviewsByPredicate(
                        mentee,
                        interview -> interview.getMeeting().getDateTime().isAfter(LocalDateTime.now())))
                .build();
    }

    private List<InterviewDTO> getInterviewsByPredicate(User mentee, Predicate<Interview> interviewPredicate) {
        return mentee.getInterviews().stream()
                .filter(interviewPredicate)
                .map(interviewConverter::toDTO)
                .toList();
    }

}
