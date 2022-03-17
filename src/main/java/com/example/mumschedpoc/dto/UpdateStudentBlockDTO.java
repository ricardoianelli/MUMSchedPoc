package com.example.mumschedpoc.dto;

import java.util.ArrayList;
import java.util.List;

public class UpdateStudentBlockDTO {
    public Integer blockId;
    public List<UpdateBlockCoursePriorityDTO> coursePriorities = new ArrayList<>();
}
