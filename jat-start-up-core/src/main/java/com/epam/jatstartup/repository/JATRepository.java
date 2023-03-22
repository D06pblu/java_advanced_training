package com.epam.jatstartup.repository;

import com.epam.jatstartup.entity.JAT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JATRepository extends JpaRepository<JAT, Integer> {
}
