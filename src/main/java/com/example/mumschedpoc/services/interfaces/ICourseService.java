package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.entities.Course;
import com.example.mumschedpoc.dto.NewCourseDTO;
import com.example.mumschedpoc.dto.CourseDTO;

import java.util.List;


public interface ICourseService {
    List<CourseDTO> findAll();

    CourseDTO findById(Integer id);

    CourseDTO insert(NewCourseDTO courseRequest);

    void delete(Integer id);

    CourseDTO update(Integer id, CourseDTO updateCourseRequest);

}
