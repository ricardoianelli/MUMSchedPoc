package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.dto.*;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();

    UserDTO findById(Integer id);

    UserDTO insert(NewUserDTO userRequest);

    void delete(Integer id);

    UserDTO update(Integer id, UserDTO updateUserRequest);

    UserDTO findByEmail(String email);

    List<UserDTO> findByUserRole(Integer roleId);

    FacultyCoursesDTO getFacultyCourses();

    FacultyCoursesDTO updateFacultyCourses(UpdateFacultyCoursesDTO updateFacultyCoursesDTO);

    StudentBlocksDTO getStudentBlocks();
}
