package com.example.mumschedpoc.repositories;

import com.example.mumschedpoc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
