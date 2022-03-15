package com.example.mumschedpoc.services;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.entities.enums.UserRole;
import com.example.mumschedpoc.repositories.IUserRepository;
import com.example.mumschedpoc.controllers.dto.UpdateUserRequest;
import com.example.mumschedpoc.controllers.dto.UserCreationRequest;
import com.example.mumschedpoc.services.exceptions.DatabaseException;
import com.example.mumschedpoc.services.exceptions.InvalidEmailException;
import com.example.mumschedpoc.services.exceptions.ResourceNotFoundException;
import com.example.mumschedpoc.services.interfaces.IUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User findByEmail(String email) {
        Optional<User> user = repository.findByEmail(email);
        return user.orElseThrow(() -> new InvalidEmailException(email));
    }

    public User insert(UserCreationRequest userRequest) {
        UserRole userRole = UserRole.valueOf(userRequest.userRoleId);
        User user = new User(null, userRequest.name, userRole, userRequest.email, userRequest.password);
        return repository.save(user);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    public User update(Long id, UpdateUserRequest updateUserRequest) {
        try {
            User user = findById(id);
            updateUser(user, updateUserRequest);
            return repository.save(user);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException(id);
        } catch (JpaObjectRetrievalFailureException ex) {
            System.out.println(ex.getClass().getSimpleName());
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateUser(User user, UpdateUserRequest updateUserRequest) {
        if (updateUserRequest.name != null) user.setName(updateUserRequest.name);
        if (updateUserRequest.email != null) user.setEmail(updateUserRequest.email);
        if (updateUserRequest.userRoleId != 0)
            user.setUserRole(UserRole.valueOf(updateUserRequest.userRoleId));
    }
}
