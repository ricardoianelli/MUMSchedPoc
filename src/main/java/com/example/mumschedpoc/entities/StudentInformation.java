package com.example.mumschedpoc.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_student_information")
public class StudentInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "studentInformation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentBlock> studentBlocks = new ArrayList<>();

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

    public List<StudentBlock> getStudentBlocks() {
        return studentBlocks;
    }

    public void setStudentBlocks(List<StudentBlock> studentBlocks) {
        this.studentBlocks = studentBlocks;
    }

    public void clearStudentBlocks() {
        this.studentBlocks.clear();
    }
}
