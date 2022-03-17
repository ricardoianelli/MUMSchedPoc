package com.example.mumschedpoc.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_student_block")
public class StudentBlock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer blockId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "student_information_id")
    private StudentInformation studentInformation;

    @OneToMany(mappedBy = "studentBlock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlockCoursePriority> coursePriorities = new ArrayList<>();

    public StudentBlock() {
    }

    public StudentBlock(Integer id, Integer blockId, StudentInformation studentInformation) {
        this.id = id;
        this.blockId = blockId;
        this.studentInformation = studentInformation;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public List<BlockCoursePriority> getCoursePriorities() {
        return coursePriorities;
    }

    public void setCoursePriorities(List<BlockCoursePriority> coursePriorities) {
        this.coursePriorities = coursePriorities;
    }

    public StudentInformation getStudentInformation() {
        return studentInformation;
    }

    public void setStudentInformation(StudentInformation studentInformation) {
        this.studentInformation = studentInformation;
    }
}
