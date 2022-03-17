package com.example.mumschedpoc.dto;

import com.example.mumschedpoc.entities.Block;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlockDTO {
    public Integer id;

    @JsonFormat(pattern="yyyy-MM-dd")
    public LocalDate startDate;
    public List<BlockCourseDTO> blockCourses = new ArrayList();

    public BlockDTO() {
    }

    //TODO: DTOs should NOT know about the entity and vice-versa, but for simplicity let's just do it for now
    public BlockDTO(Block block) {
        this.id = block.getId();
        this.startDate = block.getStartDate();
        this.blockCourses = block.getBlockCourses().stream().map(c -> new BlockCourseDTO(c)).collect(Collectors.toList());
    }
}
