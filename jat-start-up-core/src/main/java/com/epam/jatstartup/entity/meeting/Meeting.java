package com.epam.jatstartup.entity.meeting;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Objects of this entity should not be created manually.
 * It will be created automatically with dependent entities such as
 * {@link Interview}, {@link Scrum}, {@link QA}, {@link Brainstorm}
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "meeting")
public class Meeting {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "meeting_series_id")
    private MeetingSeries series;

    @Column(name = "teams_link")
    private String teamsLink;

    @Column(name = "language")
    private String languageCode;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private QA qa;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Brainstorm brainstorm;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Interview interview;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Scrum scrum;

}
