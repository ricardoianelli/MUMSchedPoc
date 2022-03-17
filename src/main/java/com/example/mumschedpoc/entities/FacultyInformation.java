package com.example.mumschedpoc.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_faculty_information")
public class FacultyInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name="faculty_preferred_courses",
            joinColumns=@JoinColumn(name="faculty_information_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="course_id", referencedColumnName="id"))
    private List<Course> preferredCourses = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public List<Course> getPreferredCourses() {
        return preferredCourses;
    }

    public void addPreferredCourse(Course course) {
        this.preferredCourses.add(course);
    }

    public void removePreferredCourse(Course course) {
        this.preferredCourses.remove(course);
    }

    public void setPreferredCourses(List<Course> preferredCourses) {
        this.preferredCourses = preferredCourses;
    }
}
