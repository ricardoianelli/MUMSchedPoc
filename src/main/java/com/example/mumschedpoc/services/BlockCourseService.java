package com.example.mumschedpoc.services;

import com.example.mumschedpoc.dto.BlockCourseDTO;
import com.example.mumschedpoc.entities.BlockCourse;
import com.example.mumschedpoc.repositories.IBlockCourseRepository;
import com.example.mumschedpoc.services.interfaces.IBlockCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockCourseService implements IBlockCourseService {

    private final IBlockCourseRepository repository;

    public BlockCourseService(IBlockCourseRepository repository) {
        this.repository = repository;
    }

    public List<BlockCourseDTO> findAll() {
        List<BlockCourse> blocks = repository.findAll();
        return blocks.stream().map(BlockCourseDTO::new).collect(Collectors.toList());
    }
}
