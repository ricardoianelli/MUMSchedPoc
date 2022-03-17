package com.example.mumschedpoc.dto;

import java.util.ArrayList;
import java.util.List;

public class FacultyCoursesDTO {
    public Integer facultyId;
    public List<CourseDTO> preferredCourses = new ArrayList<>();

    public FacultyCoursesDTO() {
    }

    public FacultyCoursesDTO(Integer facultyId) {
        this.facultyId = facultyId;
    }
}
