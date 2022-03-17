package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.dto.BlockCourseDTO;

import java.util.List;

public interface IBlockCourseService {
    List<BlockCourseDTO> findAll();
}
