package com.example.mumschedpoc.repositories;

import com.example.mumschedpoc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(@Param("user_email")  String email);
}
