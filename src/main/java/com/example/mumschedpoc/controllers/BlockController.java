package com.example.mumschedpoc.controllers;

import com.example.mumschedpoc.dto.BlockDTO;
import com.example.mumschedpoc.services.interfaces.IBlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    @Operation(summary="Add block")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BlockDTO> insert(@RequestBody BlockDTO blockDTO) {
        BlockDTO response = service.insert(blockDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary="Get block")
    public ResponseEntity<BlockDTO> findById(@PathVariable Integer id) {
        BlockDTO block = service.findById(id);
        return ResponseEntity.ok().body(block);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary="Delete block")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary="Update block")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BlockDTO> update(@PathVariable Integer id, @RequestBody BlockDTO updateBlockDTO) {
        BlockDTO blockDTO = service.update(id, updateBlockDTO);
        return ResponseEntity.ok(blockDTO);
    }
}
