package com.example.mumschedpoc.controllers;

import com.example.mumschedpoc.dto.BlockCourseDTO;
import com.example.mumschedpoc.dto.NewBlockCourseDTO;
import com.example.mumschedpoc.services.interfaces.IBlockCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    @Operation(summary="Add block course")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BlockCourseDTO> insert(@RequestBody NewBlockCourseDTO blockCourseDTO) {
        BlockCourseDTO response = service.insert(blockCourseDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary="Get block course")
    public ResponseEntity<BlockCourseDTO> findById(@PathVariable Integer id) {
        BlockCourseDTO blockCourse = service.findById(id);
        return ResponseEntity.ok().body(blockCourse);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary="Delete block course")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary="Update block course")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BlockCourseDTO> update(@PathVariable Integer id, @RequestBody NewBlockCourseDTO updateBlockCourseDTO) {
        BlockCourseDTO blockCourseDTO = service.update(id, updateBlockCourseDTO);
        return ResponseEntity.ok(blockCourseDTO);
    }
}
