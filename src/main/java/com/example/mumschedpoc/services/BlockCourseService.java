package com.example.mumschedpoc.services;

import com.example.mumschedpoc.dto.BlockCourseDTO;
import com.example.mumschedpoc.entities.BlockCourse;
import com.example.mumschedpoc.repositories.IBlockCourseRepository;
import com.example.mumschedpoc.repositories.IBlockRepository;
import com.example.mumschedpoc.repositories.ICourseRepository;
import com.example.mumschedpoc.repositories.IUserRepository;
import com.example.mumschedpoc.services.exceptions.ResourceNotFoundException;
import com.example.mumschedpoc.services.interfaces.IBlockCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockCourseService implements IBlockCourseService {

    private final ICourseRepository courseRepository;
    private final IBlockRepository blockRepository;
    private final IUserRepository userRepository;

    private final IBlockCourseRepository repository;

    public BlockCourseService(ICourseRepository courseRepository, IBlockRepository blockRepository, IUserRepository userRepository, IBlockCourseRepository repository) {
        this.courseRepository = courseRepository;
        this.blockRepository = blockRepository;
        this.userRepository = userRepository;
        this.repository = repository;
    }

    public List<BlockCourseDTO> findAll() {
        List<BlockCourse> blocks = repository.findAll();
        return blocks.stream().map(BlockCourseDTO::new).collect(Collectors.toList());
    }

    //TODO: Improve
    public BlockCourse fromDTO(BlockCourseDTO dto) {
        BlockCourse course = new BlockCourse(dto.id,
                blockRepository.findById(dto.blockId).orElseThrow(() -> new ResourceNotFoundException(dto.blockId)),
                courseRepository.findById(dto.courseId).orElseThrow(() -> new ResourceNotFoundException(dto.courseId)),
                userRepository.findById(dto.facultyId).orElseThrow(() -> new ResourceNotFoundException(dto.facultyId)),
                dto.availableSeats);

        return course;
    }
}
