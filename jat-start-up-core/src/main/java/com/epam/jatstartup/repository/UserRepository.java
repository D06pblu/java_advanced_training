package com.epam.jatstartup.repository;

import com.epam.jatstartup.entity.participant.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Set<User> findAllByParticipationInfosRolesName(String roleName);
    Optional<User> findByEmail(String email);

}
