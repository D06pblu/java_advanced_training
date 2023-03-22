package com.epam.jatstartup.entity.meeting;

import com.epam.jatstartup.entity.learn.Feedback;
import com.epam.jatstartup.entity.learn.Gap;
import com.epam.jatstartup.entity.learn.Theme;
import com.epam.jatstartup.entity.participant.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "interview")
public class Interview {

    @Id
    @Column(name = "id")
    private Long id;

    @JsonManagedReference
    @OneToOne(mappedBy = "qa")
    @MapsId
    @JoinColumn(name = "id")
    private Meeting meeting;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private InterviewType type;

    @JsonManagedReference(value = "gap_interviewee")
    @ManyToOne
    @JoinColumn(name = "interviewee_id", referencedColumnName = "id")
    private User interviewee;

    @ManyToMany
    @JoinTable(name = "m_m_interview_participant",
            joinColumns = @JoinColumn(name = "interview_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<User> participants; //contains experts and spectators

    @ManyToMany
    @JoinTable(name = "m_m_interview_theme",
            joinColumns = @JoinColumn(name = "interview_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private List<Theme> themes;

    @OneToMany
    @JoinColumn(name = "interview_id",
            referencedColumnName = "id",
            nullable = false)
    private List<Gap> gaps;

    @OneToMany(mappedBy = "interview")
    private List<Feedback> feedbacks;

}
