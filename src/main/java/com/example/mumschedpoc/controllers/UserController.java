package com.example.mumschedpoc.controllers;

import com.example.mumschedpoc.dto.*;
import com.example.mumschedpoc.entities.StudentBlock;
import com.example.mumschedpoc.entities.StudentInformation;
import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.services.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
@Tag(name="User")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary="List users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> findAll(@RequestParam(value="role", defaultValue = "") Integer roleId) {
        List<UserDTO> list = new ArrayList<>();

        if (roleId == 0) {
            list = service.findAll();
        }
        else
        {
            list = service.findByUserRole(roleId);
        }

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary="Get User", responses = {
            @ApiResponse(description = "User found", responseCode = "200",
            content = @Content(schema = @Schema(implementation = User.class))),

            @ApiResponse(description = "User not found", responseCode = "404",
                    content = @Content)
    })
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        UserDTO user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @Operation(summary="Add User", responses = {
            @ApiResponse(description = "User created", responseCode = "201",
                    content = @Content),
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDTO> insert(@RequestBody NewUserDTO userRequest) {
        UserDTO responseUser = service.insert(userRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(responseUser.id).toUri();

        return ResponseEntity.created(uri).body(responseUser);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary="Delete User", responses = {
            @ApiResponse(description = "User deleted", responseCode = "200",
                    content = @Content),

            @ApiResponse(description = "User not found", responseCode = "404",
                    content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary="Update User", responses = {
            @ApiResponse(description = "User updated", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),

            @ApiResponse(description = "User not found", responseCode = "404",
                    content = @Content)
    })
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO updateUserRequest) {
        UserDTO user = service.update(id, updateUserRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/faculty/courses")
    @Operation(summary="Get Faculty Courses")
    public ResponseEntity<FacultyCoursesDTO> getAllCourses() {
        FacultyCoursesDTO coursesDTO = service.getFacultyCourses();
        return ResponseEntity.ok().body(coursesDTO);
    }

    @PutMapping(value = "/faculty/courses")
    @Operation(summary="Update Faculty Courses")
    public ResponseEntity<FacultyCoursesDTO> update(@RequestBody UpdateFacultyCoursesDTO updateFacultyCoursesDTO) {
        FacultyCoursesDTO user = service.updateFacultyCourses(updateFacultyCoursesDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/student/blocks")
    @Operation(summary="Get Student Blocks")
    public ResponseEntity<StudentBlocksDTO> getStudentBlocks() {
        StudentBlocksDTO studentBlocks = service.getStudentBlocks();
        return ResponseEntity.ok().body(studentBlocks);
    }
}
