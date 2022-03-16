package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.entities.Course;
import com.example.mumschedpoc.dto.NewCourseDTO;
import com.example.mumschedpoc.dto.CourseDTO;

import java.util.List;


public interface ICourseService {
    List<Course> findAll();

    Course findById(Integer id);

    Course insert(NewCourseDTO courseRequest);

    void delete(Integer id);

    Course update(Integer id, CourseDTO updateCourseRequest);

}
