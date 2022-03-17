package com.example.mumschedpoc.controllers;

import com.example.mumschedpoc.dto.BlockCourseDTO;
import com.example.mumschedpoc.services.interfaces.IBlockCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/block_courses")
@Tag(name="Block Courses")
public class BlockCourseController {
    private final IBlockCourseService service;

    public BlockCourseController(IBlockCourseService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary="List block courses")
    public ResponseEntity<List<BlockCourseDTO>> findAll() {
        List<BlockCourseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
