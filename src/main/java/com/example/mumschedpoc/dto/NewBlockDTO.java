package com.example.mumschedpoc.dto;

import com.example.mumschedpoc.entities.Block;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewBlockDTO {
    public Integer id;

    @JsonFormat(pattern="MM-dd-yyyy")
    public LocalDate startDate;
    public List<NewBlockCourseDTO> blockCourses = new ArrayList();

    public NewBlockDTO() {
    }

    //TODO: DTOs should NOT know about the entity and vice-versa, but for simplicity let's just do it for now
    public NewBlockDTO(Block block) {
        this.id = block.getId();
        this.startDate = block.getStartDate();
        this.blockCourses = block.getBlockCourses().stream().map(c -> new NewBlockCourseDTO(c)).collect(Collectors.toList());
    }
}
