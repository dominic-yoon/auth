package com.dominic.auth.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominic.auth.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
