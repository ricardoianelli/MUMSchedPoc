package com.example.mumschedpoc.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_block")
public class Block implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "block")
    private List<BlockCourse> blockCourses = new ArrayList<>();

    public Block() {}

    public Block(Integer id) {
        this.id = id;
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
}
