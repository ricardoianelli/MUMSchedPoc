package com.example.mumschedpoc.repositories;

import com.example.mumschedpoc.entities.AdminInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminInformationRepository extends JpaRepository<AdminInformation, Integer> {
}
