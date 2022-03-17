package com.example.mumschedpoc.services;

import com.example.mumschedpoc.dto.*;
import com.example.mumschedpoc.entities.Course;
import com.example.mumschedpoc.entities.FacultyInformation;
import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.entities.enums.UserRole;
import com.example.mumschedpoc.repositories.ICourseRepository;
import com.example.mumschedpoc.repositories.IUserRepository;
import com.example.mumschedpoc.security.SpringSecurityUser;
import com.example.mumschedpoc.services.exceptions.AuthorizationException;
import com.example.mumschedpoc.services.exceptions.DatabaseException;
import com.example.mumschedpoc.services.exceptions.InvalidEmailException;
import com.example.mumschedpoc.services.exceptions.ResourceNotFoundException;
import com.example.mumschedpoc.services.interfaces.IUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;
    private final ICourseRepository courseRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(IUserRepository repository, ICourseRepository courseRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static SpringSecurityUser getAuthenticatedUser() {
        try {
            return (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO findById(Integer id) {
        User user = getUserById(id);
        return new UserDTO(user);
    }

    public UserDTO findByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow(() -> new InvalidEmailException(email));
        return new UserDTO(user);
    }

    @Override
    public List<UserDTO> findByUserRole(Integer roleId) {
        List<User> users = repository.findByUserRole(roleId);
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public FacultyCoursesDTO getFacultyCourses() {
        SpringSecurityUser authenticatedUser = UserService.getAuthenticatedUser();
        Integer userId = authenticatedUser.getId();
        User user = repository.getById(userId);
        FacultyCoursesDTO coursesDTO = new FacultyCoursesDTO();
        coursesDTO.facultyId = userId;
        for (Course course: user.getFacultyInformation().getPreferredCourses()) {
            coursesDTO.preferredCourses.add(new CourseDTO(course));
        }
        return coursesDTO;
    }

    @Override
    public FacultyCoursesDTO updateFacultyCourses(UpdateFacultyCoursesDTO updateFacultyCoursesDTO) {
        SpringSecurityUser authenticatedUser = UserService.getAuthenticatedUser();
        Integer userId = authenticatedUser.getId();
        User user = repository.getById(userId);
        FacultyInformation facultyInformation = user.getFacultyInformation();
        List<Course> newCourses = new ArrayList<>();

        FacultyCoursesDTO coursesDTO = new FacultyCoursesDTO();
        coursesDTO.facultyId = userId;

        for (Integer courseId: updateFacultyCoursesDTO.preferredCoursesIds) {
            Course newCourse = courseRepository.getById(courseId);
            coursesDTO.preferredCourses.add(new CourseDTO(newCourse));
            newCourses.add(newCourse);
        }

        facultyInformation.setPreferredCourses(newCourses);
        return coursesDTO;
    }

    public UserDTO insert(NewUserDTO userRequest) {
        UserRole userRole = UserRole.toEnum(userRequest.userRoleId);
        User user = new User(null, userRequest.name, userRole, userRequest.email, passwordEncoder.encode(userRequest.password));
        user = repository.save(user);
        return new UserDTO(user);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    public UserDTO update(Integer id, UserDTO updateUserRequest) {
        try {
            User user = getUserById(id);
            updateUser(user, updateUserRequest);
            user = repository.save(user);
            return new UserDTO(user);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException(id);
        } catch (JpaObjectRetrievalFailureException ex) {
            System.out.println(ex.getClass().getSimpleName());
            throw new ResourceNotFoundException(id);
        }
    }

    private User getUserById(Integer id) {
        SpringSecurityUser authenticatedUser = UserService.getAuthenticatedUser();
        if (authenticatedUser==null || !authenticatedUser.hasRole(UserRole.ADMIN) && !id.equals(authenticatedUser.getId())) {
            throw new AuthorizationException("Access Denied");
        }

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private void updateUser(User user, UserDTO updateUserRequest) {
        if (updateUserRequest.name != null) user.setName(updateUserRequest.name);
        if (updateUserRequest.email != null) user.setEmail(updateUserRequest.email);
        if (updateUserRequest.userRoleId != 0)
            user.setUserRole(UserRole.toEnum(updateUserRequest.userRoleId));
    }
}
