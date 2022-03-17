package com.example.mumschedpoc.entities;

import com.example.mumschedpoc.entities.enums.UserRole;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_block_course")
public class BlockCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="block_id")
    private Block block;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_id")
    private Course course;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="faculty_id")
    private User faculty;

    private Integer availableSeats;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public BlockCourse() {}

    public BlockCourse(Integer id, Block block, Course course, User faculty, Integer availableSeats) {
        this.id = id;
        this.block = block;
        this.course = course;
        this.faculty = faculty;
        this.availableSeats = availableSeats;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getFaculty() {
        return faculty;
    }

    public void setFaculty(User faculty) {
        if (faculty.getUserRole().equals(UserRole.FACULTY)) {
            this.faculty = faculty;
        } else {
            throw new IllegalArgumentException("Only a Faculty user can be assigned as faculty for a block course");
        }
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
