package com.epam.jatstartup.entity.learn;

import com.epam.jatstartup.entity.meeting.Interview;
import com.epam.jatstartup.entity.participant.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pros")
    private String pros;

    @Column(name = "contras")
    private String contras;

    @Column(name = "mark")
    private double mark;

    @ManyToOne
    @JoinColumn(name = "expert_id", referencedColumnName = "id")
    private User expert;

    @ManyToOne
    @JoinColumn(name = "interview_id", referencedColumnName = "id")
    private Interview interview;
}
