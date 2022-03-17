package com.example.mumschedpoc.entities;


import java.util.ArrayList;
import java.util.List;

public class StudentBlock {
    private Integer id;
    private Integer blockId;
    private List<Integer> orderedBlockCourseIds = new ArrayList<>();

    public StudentBlock() {
    }

    public StudentBlock(Integer id, Integer blockId) {
        this.id = id;
        this.blockId = blockId;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public List<Integer> getOrderedBlockCourseIds() {
        return orderedBlockCourseIds;
    }

    public void setOrderedBlockCourseIds(List<Integer> orderedBlockCourseIds) {
        this.orderedBlockCourseIds = orderedBlockCourseIds;
    }

    public void addBlockCourseId(Integer id) {
        this.orderedBlockCourseIds.add(id);
    }

    public void removeBlockCourseId(Integer id) {
        this.orderedBlockCourseIds.remove(id);
    }

    public void clearBlockCourseIds() {
        this.orderedBlockCourseIds.clear();
    }
}
