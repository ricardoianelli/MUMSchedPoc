package com.example.mumschedpoc.services;

import com.example.mumschedpoc.dto.BlockCourseDTO;
import com.example.mumschedpoc.dto.BlockDTO;
import com.example.mumschedpoc.entities.Block;
import com.example.mumschedpoc.entities.BlockCourse;
import com.example.mumschedpoc.repositories.IBlockRepository;
import com.example.mumschedpoc.services.exceptions.DatabaseException;
import com.example.mumschedpoc.services.exceptions.ResourceNotFoundException;
import com.example.mumschedpoc.services.interfaces.IBlockCourseService;
import com.example.mumschedpoc.services.interfaces.IBlockService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockService implements IBlockService {

    private final IBlockCourseService blockCourseService;
    private final IBlockRepository repository;

    public BlockService(IBlockCourseService blockCourseService, IBlockRepository repository) {
        this.blockCourseService = blockCourseService;
        this.repository = repository;
    }

    public List<BlockDTO> findAll() {
        List<Block> blocks = repository.findAll();
        return blocks.stream().map(BlockDTO::new).collect(Collectors.toList());
    }

    @Override
    public BlockDTO findById(Integer id) {
        Block block = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return new BlockDTO(block);
    }

    @Override
    public BlockDTO insert(BlockDTO blockRequest) {
        Block block = new Block(null, blockRequest.startDate);
        for (BlockCourseDTO courseDTO: blockRequest.blockCourses) {
            BlockCourse blockCourse = blockCourseService.fromDTO(courseDTO);
            block.addBlockCourse(blockCourse);
        }
        block = repository.save(block);
        return new BlockDTO(block);
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
    public BlockDTO update(Integer id, BlockDTO updateBlockDTO) {
        try {
            Block block = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateBlock(block, updateBlockDTO);
            block = repository.save(block);
            return new BlockDTO(block);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException(id);
        } catch (JpaObjectRetrievalFailureException ex) {
            System.out.println(ex.getClass().getSimpleName());
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateBlock(Block block, BlockDTO updateBlockDTO) {
        if (updateBlockDTO.startDate != null)
            block.setStartDate(updateBlockDTO.startDate);

        if (updateBlockDTO.blockCourses.isEmpty()) return;

        block.clearBlockCourses();

        for (BlockCourseDTO courseDto : updateBlockDTO.blockCourses) {
            BlockCourse blockCourse = blockCourseService.fromDTO(courseDto);
            block.addBlockCourse(blockCourse);
        }
    }
}
