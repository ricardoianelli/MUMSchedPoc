package com.example.mumschedpoc.controllers;

import com.example.mumschedpoc.dto.BlockDTO;
import com.example.mumschedpoc.services.interfaces.IBlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/blocks")
@Tag(name="Blocks")
public class BlockController {
    private final IBlockService service;

    public BlockController(IBlockService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary="List blocks")
    public ResponseEntity<List<BlockDTO>> findAll() {
        List<BlockDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
