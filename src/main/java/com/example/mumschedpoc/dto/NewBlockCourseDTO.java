package com.example.mumschedpoc.dto;

import com.example.mumschedpoc.entities.BlockCourse;

public class NewBlockCourseDTO {
    public Integer id;
    public Integer courseId;
    public Integer facultyId;
    public Integer blockId;
    public Integer availableSeats;

    public NewBlockCourseDTO() {}

    //TODO: DTOs should NOT know about the entity and vice-versa, but for simplicity let's just do it for now
    public NewBlockCourseDTO(BlockCourse course) {
        this.id = course.getId();
        this.courseId = course.getCourse().getId();
        this.facultyId = course.getFaculty().getId();
        this.blockId = course.getBlock().getId();
        this.availableSeats = course.getAvailableSeats();
    }
}
