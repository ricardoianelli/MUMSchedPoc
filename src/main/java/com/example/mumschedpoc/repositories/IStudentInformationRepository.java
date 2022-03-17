package com.example.mumschedpoc.repositories;

import com.example.mumschedpoc.entities.StudentInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentInformationRepository extends JpaRepository<StudentInformation, Integer> {
    StudentInformation findByUserId(Integer id);
}
