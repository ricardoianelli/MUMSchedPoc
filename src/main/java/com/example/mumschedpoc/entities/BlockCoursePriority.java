package com.example.mumschedpoc.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_block_course_priority")
public class BlockCoursePriority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer priority;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "block_course_id")
    private BlockCourse blockCourse;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "student_block_id")
    private StudentBlock studentBlock;

    public BlockCoursePriority() {
    }

    public BlockCoursePriority(Integer id, Integer priority, BlockCourse blockCourse, StudentBlock studentBlock) {
        this.id = id;
        this.priority = priority;
        this.blockCourse = blockCourse;
        this.studentBlock = studentBlock;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BlockCourse getBlockCourse() {
        return blockCourse;
    }

    public void setBlockCourse(BlockCourse blockCourse) {
        this.blockCourse = blockCourse;
    }

    public StudentBlock getStudentBlock() {
        return studentBlock;
    }

    public void setStudentBlock(StudentBlock studentBlock) {
        this.studentBlock = studentBlock;
    }
}
