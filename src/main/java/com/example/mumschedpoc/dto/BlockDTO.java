package com.example.mumschedpoc.dto;

import com.example.mumschedpoc.entities.Block;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlockDTO {
    public Integer id;

    @JsonFormat(pattern="MM-dd-yyyy")
    public LocalDate startDate;
    public List<BlockCourseDTO> courses = new ArrayList();

    public BlockDTO() {
    }

    public BlockDTO(Block block) {
        this.id = block.getId();
        this.startDate = block.getStartDate();
        this.courses = block.getBlockCourses().stream().map(c -> new BlockCourseDTO(c)).collect(Collectors.toList());
    }
}
