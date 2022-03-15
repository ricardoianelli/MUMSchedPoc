package com.example.mumschedpoc.services;

import com.example.mumschedpoc.entities.Course;
import com.example.mumschedpoc.repositories.ICourseRepository;
import com.example.mumschedpoc.controllers.dto.CourseCreationRequest;
import com.example.mumschedpoc.controllers.dto.UpdateCourseRequest;
import com.example.mumschedpoc.services.exceptions.DatabaseException;
import com.example.mumschedpoc.services.exceptions.ResourceNotFoundException;
import com.example.mumschedpoc.services.interfaces.ICourseService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    private final ICourseRepository repository;

    public CourseService(ICourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Course findById(Integer id) {
        Optional<Course> course = repository.findById(id);
        return course.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Course insert(CourseCreationRequest courseRequest) {
        Course course = new Course(null, courseRequest.code, courseRequest.name);
        return repository.save(course);
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

    public Course update(Integer id, UpdateCourseRequest updateCourseRequest) {
        try {
            Course course = findById(id);
            updateCourse(course, updateCourseRequest);
            return repository.save(course);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException(id);
        } catch (JpaObjectRetrievalFailureException ex) {
            System.out.println(ex.getClass().getSimpleName());
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateCourse(Course course, UpdateCourseRequest updateCourseRequest) {
        if (updateCourseRequest.code != null)
            course.setCode(updateCourseRequest.code);
        if (updateCourseRequest.name != null)
            course.setName(updateCourseRequest.name);
    }
}
