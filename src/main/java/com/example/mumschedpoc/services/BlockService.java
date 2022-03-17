package com.example.mumschedpoc.services;

import com.example.mumschedpoc.dto.BlockCourseDTO;
import com.example.mumschedpoc.dto.BlockDTO;
import com.example.mumschedpoc.entities.Block;
import com.example.mumschedpoc.entities.BlockCourse;
import com.example.mumschedpoc.repositories.IBlockRepository;
import com.example.mumschedpoc.services.exceptions.ResourceNotFoundException;
import com.example.mumschedpoc.services.interfaces.IBlockCourseService;
import com.example.mumschedpoc.services.interfaces.IBlockService;
import org.springframework.stereotype.Service;

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

    }

    @Override
    public BlockDTO update(Integer id, BlockDTO updateBlockDTO) {
        return null;
    }
}
