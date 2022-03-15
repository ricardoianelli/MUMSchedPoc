package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.entities.Course;
import com.example.mumschedpoc.controllers.dto.CourseCreationRequest;
import com.example.mumschedpoc.controllers.dto.UpdateCourseRequest;

import java.util.List;


public interface ICourseService {
    List<Course> findAll();

    Course findById(Integer id);

    Course insert(CourseCreationRequest courseRequest);

    void delete(Integer id);

    Course update(Integer id, UpdateCourseRequest updateCourseRequest);

}
