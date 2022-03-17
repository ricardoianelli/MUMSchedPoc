package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.dto.BlockDTO;

import java.util.List;

public interface IBlockService {
    List<BlockDTO> findAll();
}
