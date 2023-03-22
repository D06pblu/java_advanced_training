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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "scrum")
public class Scrum {

    @Id
    @Column(name = "id")
    private Long id;

    @JsonManagedReference
    @OneToOne(mappedBy = "qa")
    @MapsId
    @JoinColumn(name = "id")
    private Meeting meeting;

    @ManyToMany
    @JoinTable(name = "m_m_scrum_participant",
            joinColumns = @JoinColumn(name = "scrum_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<User> participants;

}