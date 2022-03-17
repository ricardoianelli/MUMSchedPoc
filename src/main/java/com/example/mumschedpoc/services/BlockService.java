package com.example.mumschedpoc.services;

import com.example.mumschedpoc.dto.BlockDTO;
import com.example.mumschedpoc.entities.Block;
import com.example.mumschedpoc.repositories.IBlockRepository;
import com.example.mumschedpoc.services.interfaces.IBlockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockService implements IBlockService {


    private final IBlockRepository repository;

    public BlockService(IBlockRepository repository) {
        this.repository = repository;
    }

    public List<BlockDTO> findAll() {
        List<Block> blocks = repository.findAll();
        return blocks.stream().map(BlockDTO::new).collect(Collectors.toList());
    }
}
