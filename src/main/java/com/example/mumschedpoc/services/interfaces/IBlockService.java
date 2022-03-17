package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.dto.BlockDTO;
import com.example.mumschedpoc.dto.NewBlockDTO;

import java.util.List;

public interface IBlockService {
    List<BlockDTO> findAll();

    BlockDTO findById(Integer id);

    BlockDTO insert(NewBlockDTO blockRequest);

    void delete(Integer id);

    BlockDTO update(Integer id, NewBlockDTO updateBlockDTO);
}
