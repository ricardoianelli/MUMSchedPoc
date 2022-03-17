package com.example.mumschedpoc.services;

import com.example.mumschedpoc.dto.CourseDTO;
import com.example.mumschedpoc.dto.NewCourseDTO;
import com.example.mumschedpoc.entities.Course;
import com.example.mumschedpoc.repositories.ICourseRepository;
import com.example.mumschedpoc.services.exceptions.DatabaseException;
import com.example.mumschedpoc.services.exceptions.ResourceNotFoundException;
import com.example.mumschedpoc.services.interfaces.ICourseService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    private final ICourseRepository repository;

    public CourseService(ICourseRepository repository) {
        this.repository = repository;
    }

    public List<CourseDTO> findAll() {
        List<Course> courses = repository.findAll();
        return courses.stream().map(CourseDTO::new).collect(Collectors.toList());
    }

    public CourseDTO findById(Integer id) {
        Course course = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return new CourseDTO(course);
    }

    public CourseDTO insert(NewCourseDTO courseRequest) {
        Course course = new Course(null, courseRequest.code, courseRequest.name, courseRequest.description);
        course = repository.save(course);
        return new CourseDTO(course);
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

    public CourseDTO update(Integer id, CourseDTO updateCourseRequest) {
        try {
            Course course = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateCourse(course, updateCourseRequest);
            course = repository.save(course);
            return new CourseDTO(course);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException(id);
        } catch (JpaObjectRetrievalFailureException ex) {
            System.out.println(ex.getClass().getSimpleName());
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateCourse(Course course, CourseDTO updateCourseRequest) {
        if (updateCourseRequest.code != null)
            course.setCode(updateCourseRequest.code);
        if (updateCourseRequest.name != null)
            course.setName(updateCourseRequest.name);
        if (updateCourseRequest.description != null)
            course.setDescription(updateCourseRequest.description);

        if (updateCourseRequest.preRequisites.isEmpty()) return;

        course.cleanPreRequisites();

        for (String preReqCode : updateCourseRequest.preRequisites) {
            Course newPreReq = repository.findByCode(preReqCode);
            course.addPreRequisite(newPreReq);
        }
    }
}
