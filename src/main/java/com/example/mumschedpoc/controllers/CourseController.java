package com.example.mumschedpoc.controllers;

import com.example.mumschedpoc.entities.Course;
import com.example.mumschedpoc.controllers.dto.UpdateCourseRequest;
import com.example.mumschedpoc.controllers.dto.CourseCreationRequest;
import com.example.mumschedpoc.services.interfaces.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/courses")
@Tag(name="Courses")
public class CourseController {

    private final ICourseService service;

    public CourseController(ICourseService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary="List courses")
    public ResponseEntity<List<Course>> findAll() {
        List<Course> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary="Get course", responses = {
            @ApiResponse(description = "Course found", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Course.class))),

            @ApiResponse(description = "Course not found", responseCode = "404",
                    content = @Content)
    })
    public ResponseEntity<Course> findById(@PathVariable Integer id) {
        Course course = service.findById(id);
        return ResponseEntity.ok().body(course);
    }

    @PostMapping
    @Operation(summary="Add course", responses = {
            @ApiResponse(description = "Course created", responseCode = "201",
                    content = @Content),
    })
    public ResponseEntity<Course> insert(@RequestBody CourseCreationRequest courseRequest) {
        Course responseCourse = service.insert(courseRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(responseCourse.getId()).toUri();

        return ResponseEntity.created(uri).body(responseCourse);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary="Delete course", responses = {
            @ApiResponse(description = "Course deleted", responseCode = "200",
                    content = @Content),

            @ApiResponse(description = "Course not found", responseCode = "404",
                    content = @Content)
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary="Update course", responses = {
            @ApiResponse(description = "Course updated", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Course.class))),

            @ApiResponse(description = "Course not found", responseCode = "404",
                    content = @Content)
    })
    public ResponseEntity<Course> update(@PathVariable Integer id, @RequestBody UpdateCourseRequest updateCourseRequest) {
        Course course = service.update(id, updateCourseRequest);
        return ResponseEntity.ok(course);
    }
}
