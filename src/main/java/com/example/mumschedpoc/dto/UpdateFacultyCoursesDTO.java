package com.example.mumschedpoc.dto;

import java.util.ArrayList;
import java.util.List;

public class UpdateFacultyCoursesDTO {
    public Integer facultyId;
    public List<Integer> preferredCoursesIds = new ArrayList<>();

    public UpdateFacultyCoursesDTO() {
    }

}
