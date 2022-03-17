package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.dto.BlockCourseDTO;
import com.example.mumschedpoc.entities.BlockCourse;

import java.util.List;

public interface IBlockCourseService {
    List<BlockCourseDTO> findAll();

    BlockCourseDTO findById(Integer id);

    BlockCourseDTO insert(BlockCourseDTO blockCourseDTO);

    void delete(Integer id);

    BlockCourseDTO update(Integer id, BlockCourseDTO updateBlockCourseDTO);

    BlockCourse fromDTO(BlockCourseDTO dto);
}
