package com.epam.jatstartup.entity.participant;

import com.epam.jatstartup.entity.JAT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "participation_info")
public class ParticipationInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "jat_id", referencedColumnName = "id")
    private JAT jat;

    @Column(name = "participation_start")
    private LocalDate participationStart;

    @Column(name = "participation_end")
    private LocalDate participationEnd;

    @ManyToMany
    @JoinTable(name = "m_m_participation_info_participation_reason",
            joinColumns = @JoinColumn(name = "participation_info_id"),
            inverseJoinColumns = @JoinColumn(name = "participation_reason_id"))
    private List<ParticipationReason> participationReasons;

    @ManyToMany
    @JoinTable(name = "m_m_participation_info_leave_reason",
            joinColumns = @JoinColumn(name = "participation_info_id"),
            inverseJoinColumns = @JoinColumn(name = "leave_reason_id"))
    private List<LeaveReason> leaveReasons;

    @ManyToMany
    @JoinTable(name = "m_m_role_participation_info",
            joinColumns = @JoinColumn(name = "participation_info_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
