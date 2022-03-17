package com.example.mumschedpoc.dto;

import com.example.mumschedpoc.entities.BlockCourse;

public class BlockCourseDTO {
    public Integer id;
    public CourseDTO course;
    public Integer facultyId;
    public Integer blockId;

    public BlockCourseDTO() {}

    public BlockCourseDTO(BlockCourse course) {
        this.id = course.getId();
        this.course = new CourseDTO(course.getCourse());
        this.facultyId = course.getFaculty().getId();
        this.blockId = course.getBlock().getId();
    }
}
