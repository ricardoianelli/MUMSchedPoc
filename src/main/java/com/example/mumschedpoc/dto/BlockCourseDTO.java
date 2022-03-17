package com.example.mumschedpoc.dto;

import com.example.mumschedpoc.entities.Block;
import com.example.mumschedpoc.entities.BlockCourse;

import java.time.LocalDate;

public class BlockCourseDTO {
    public Integer id;
    public CourseDTO course;
    public String facultyName;
    public Integer blockId;
    public LocalDate blockStartDate;
    public Integer availableSeats;

    public BlockCourseDTO() {}

    //TODO: DTOs should NOT know about the entity and vice-versa, but for simplicity let's just do it for now
    public BlockCourseDTO(BlockCourse course) {
        this.id = course.getId();
        this.course = new CourseDTO(course.getCourse());
        this.facultyName = course.getFaculty().getName();
        this.availableSeats = course.getAvailableSeats();

        Block block = course.getBlock();
        this.blockId = block.getId();
        this.blockStartDate = block.getStartDate();
    }
}
