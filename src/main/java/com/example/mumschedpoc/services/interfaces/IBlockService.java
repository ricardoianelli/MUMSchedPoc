package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.dto.BlockDTO;

import java.util.List;

public interface IBlockService {
    List<BlockDTO> findAll();

    BlockDTO findById(Integer id);

    BlockDTO insert(BlockDTO blockRequest);

    void delete(Integer id);

    BlockDTO update(Integer id, BlockDTO updateBlockDTO);
}
