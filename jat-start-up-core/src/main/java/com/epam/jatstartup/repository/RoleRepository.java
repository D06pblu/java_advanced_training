package com.epam.jatstartup.repository;

import com.epam.jatstartup.entity.participant.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}