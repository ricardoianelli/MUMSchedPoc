package com.example.mumschedpoc.repositories;

import com.example.mumschedpoc.entities.StudentBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentBlockRepository extends JpaRepository<StudentBlock, Integer> {
}
