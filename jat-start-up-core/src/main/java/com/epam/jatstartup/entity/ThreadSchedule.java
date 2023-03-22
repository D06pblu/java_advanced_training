package com.epam.jatstartup.entity;

import com.epam.jatstartup.entity.meeting.MeetingSeries;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "thread_schedule")
public class ThreadSchedule {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "jat_id")
    private JAT jat;

    @Column(name = "thread_num")
    private int threadNumber;

    @OneToMany(mappedBy = "schedule")
    private List<MeetingSeries> meetingSeries;

}
