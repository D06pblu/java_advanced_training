package com.epam.jatstartup.entity.meeting;

import com.epam.jatstartup.entity.participant.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "brainstorm")
public class Brainstorm {

    @Id
    @Column(name = "id")
    private Long id;

    @JsonManagedReference
    @OneToOne(mappedBy = "qa")
    @MapsId
    @JoinColumn(name = "id")
    private Meeting meeting;

    @Column(name = "task")
    private String task;

    @Column(name = "task_description")
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "screen_demonstrator_id", referencedColumnName = "id")
    private User screenDemonstrator;

    @Column(name = "git_link")
    private String gitLink;

}
