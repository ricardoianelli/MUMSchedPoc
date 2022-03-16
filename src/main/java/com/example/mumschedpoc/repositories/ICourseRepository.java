package com.example.mumschedpoc.repositories;

import com.example.mumschedpoc.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {
    Course findByCode(String code);
}
