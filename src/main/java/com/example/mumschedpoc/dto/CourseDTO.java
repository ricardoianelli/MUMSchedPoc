package com.example.mumschedpoc.dto;

import com.example.mumschedpoc.entities.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseDTO {
    public Integer id;
    public String code;
    public String name;
    public String description;
    public List<String> preRequisites;

    public CourseDTO() {}

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.code = course.getCode();
        this.name = course.getName();
        this.description = course.getDescription();
        this.preRequisites = course.getPreRequisites().stream().map(Course::getCode).collect(Collectors.toList());
    }
}
