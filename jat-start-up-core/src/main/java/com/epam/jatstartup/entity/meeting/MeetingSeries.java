package com.epam.jatstartup.entity.meeting;

import com.epam.jatstartup.entity.ThreadSchedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "meeting_series")
@Entity
public class MeetingSeries {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private ThreadSchedule schedule;

    @Column(name = "start_date")
    private LocalDateTime start;

    @Column(name = "end_date")
    private LocalDateTime end;

    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "meeting_series_allowed_day",
            joinColumns = @JoinColumn(name = "meeting_series_id"))
    @Column(name = "week_day")
    private List<DayOfWeek> allowedWeekDays;

    @OneToMany(mappedBy = "series")
    private List<Meeting> meetings;

}
