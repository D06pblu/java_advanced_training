package com.epam.jatstartup.entity;

import com.epam.jatstartup.entity.participant.ParticipationInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "jat")
public class JAT {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location")
    private String countryCode;

    @Column(name = "language")
    private String languageCode;

    @OneToMany(mappedBy = "jat")
    private List<ThreadSchedule> threads;

    @OneToMany(mappedBy = "jat")
    private List<ParticipationInfo> participationInfos;

}
