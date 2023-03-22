package com.epam.jatstartup.entity.learn;

import com.epam.jatstartup.entity.participant.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "gap")
public class Gap {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "wording")
    private String wording;

    @Column(name = "done")
    private boolean done;

    @JsonBackReference(value = "gap_interviewee")
    @ManyToOne
    @JoinColumn(name = "interviewee_id", referencedColumnName = "id")
    private User interviewee;

}
