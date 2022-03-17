package com.example.mumschedpoc.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_block")
public class Block implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlockCourse> blockCourses = new ArrayList<>();

    private LocalDate startDate;

    public Block() {}

    public Block(Integer id) {
        this.id = id;
        this.startDate = LocalDate.now();
    }

    public Block(Integer id, LocalDate startDate) {
        this.id = id;
        this.startDate = startDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<BlockCourse> getBlockCourses() {
        return blockCourses;
    }

    public void addBlockCourse(BlockCourse blockCourse) {
        blockCourses.add(blockCourse);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void clearBlockCourses() {
        blockCourses.clear();
    }
}
