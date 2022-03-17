package com.example.mumschedpoc.services;

import com.example.mumschedpoc.dto.BlockCourseDTO;
import com.example.mumschedpoc.dto.BlockDTO;
import com.example.mumschedpoc.entities.Block;
import com.example.mumschedpoc.entities.BlockCourse;
import com.example.mumschedpoc.entities.Course;
import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.repositories.IBlockCourseRepository;
import com.example.mumschedpoc.repositories.IBlockRepository;
import com.example.mumschedpoc.repositories.ICourseRepository;
import com.example.mumschedpoc.repositories.IUserRepository;
import com.example.mumschedpoc.services.exceptions.DatabaseException;
import com.example.mumschedpoc.services.exceptions.ResourceNotFoundException;
import com.example.mumschedpoc.services.interfaces.IBlockCourseService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    @Override
    public BlockCourseDTO findById(Integer id) {
        BlockCourse blockCourse = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return new BlockCourseDTO(blockCourse);
    }

    @Override
    public BlockCourseDTO insert(BlockCourseDTO blockCourseDTO) {
        BlockCourse blockCourse = fromDTO(blockCourseDTO);
        blockCourse = repository.save(blockCourse);
        return new BlockCourseDTO(blockCourse);
    }

    @Override
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public BlockCourseDTO update(Integer id, BlockCourseDTO updateBlockCourseDTO) {
        try {
            BlockCourse blockCourse = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateBlockCourse(blockCourse, updateBlockCourseDTO);
            blockCourse = repository.save(blockCourse);
            return new BlockCourseDTO(blockCourse);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException(id);
        } catch (JpaObjectRetrievalFailureException ex) {
            System.out.println(ex.getClass().getSimpleName());
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateBlockCourse(BlockCourse blockCourse, BlockCourseDTO updateBlockCourseDTO) {
        if (updateBlockCourseDTO.courseId != null)
            blockCourse.setCourse(getCourseById(updateBlockCourseDTO.courseId));

        if (updateBlockCourseDTO.blockId != null)
            blockCourse.setBlock(getBlockById(updateBlockCourseDTO.blockId));

        if (updateBlockCourseDTO.facultyId != null)
            blockCourse.setFaculty(getFacultyById(updateBlockCourseDTO.facultyId));

        if (updateBlockCourseDTO.availableSeats != null)
            blockCourse.setAvailableSeats(updateBlockCourseDTO.availableSeats);
    }

    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Block getBlockById(Integer id) {
        return blockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User getFacultyById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //TODO: Improve
    public BlockCourse fromDTO(BlockCourseDTO dto) {
        BlockCourse course = new BlockCourse(dto.id,
                getBlockById(dto.blockId),
                getCourseById(dto.courseId),
                getFacultyById(dto.facultyId),
                dto.availableSeats);

        return course;
    }
}
