package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.dto.BlockCourseDTO;
import com.example.mumschedpoc.entities.BlockCourse;

import java.util.List;

public interface IBlockCourseService {
    List<BlockCourseDTO> findAll();
    BlockCourse fromDTO(BlockCourseDTO dto);
}
