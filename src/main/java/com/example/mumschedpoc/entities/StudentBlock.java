package com.example.mumschedpoc.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class StudentBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer blockId;

    //Todo: Improve, had to do like that due to time requirements
    private String orderedBlockCourseIds;

    public StudentBlock() {
    }

    public StudentBlock(Integer id, Integer blockId) {
        this.id = id;
        this.blockId = blockId;
    }

    public StudentBlock(Integer id, Integer blockId, String orderedBlockCourseIds) {
        this.id = id;
        this.blockId = blockId;
        this.orderedBlockCourseIds = orderedBlockCourseIds;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public List<Integer> getOrderedBlockCourseIds() {
        return Arrays.stream(orderedBlockCourseIds.split(",")).map(Integer::valueOf).collect(Collectors.toList());
    }

    public void setOrderedBlockCourseIds(List<Integer> orderedBlockCourseIds) {
        String asString = orderedBlockCourseIds.stream().map(i -> i.toString()).collect(Collectors.joining(","));
        this.orderedBlockCourseIds = asString;
    }


    public void clearBlockCourseIds() {
        this.orderedBlockCourseIds = "";
    }
}
