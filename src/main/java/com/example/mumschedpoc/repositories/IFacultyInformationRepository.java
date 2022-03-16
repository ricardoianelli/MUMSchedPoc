package com.example.mumschedpoc.repositories;

import com.example.mumschedpoc.entities.FacultyInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacultyInformationRepository extends JpaRepository<FacultyInformation, Integer> {
}
