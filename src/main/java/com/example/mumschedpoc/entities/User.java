package com.example.mumschedpoc.entities;

import com.example.mumschedpoc.entities.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;

    @Column(name="user_role")
    private Integer userRole;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JoinColumn(name = "adminInformationId", referencedColumnName = "id")
    private AdminInformation adminInformation;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JoinColumn(name = "facultyInformationId", referencedColumnName = "id")
    private FacultyInformation facultyInformation;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JoinColumn(name = "studentInformationId", referencedColumnName = "id")
    private StudentInformation studentInformation;

    @JsonIgnore
    private String password;

    public User() {
    }

    public User(Integer id, String name, UserRole userRole, String email, String password) {
        this.id = id;
        this.name = name;
        this.userRole = userRole.getCode();
        this.email = email;
        this.password = password;
        adminInformation = new AdminInformation();
        adminInformation.setUser(this);
        studentInformation = new StudentInformation();
        studentInformation.setUser(this);
        facultyInformation = new FacultyInformation();
        facultyInformation.setUser(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return UserRole.toEnum(userRole);
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public AdminInformation getAdminInformation() {
        return adminInformation;
    }

    public void setAdminInformation(AdminInformation adminInformation) {
        this.adminInformation = adminInformation;
    }

    public FacultyInformation getFacultyInformation() {
        return facultyInformation;
    }

    public void setFacultyInformation(FacultyInformation facultyInformation) {
        this.facultyInformation = facultyInformation;
    }

    public StudentInformation getStudentInformation() {
        return studentInformation;
    }

    public void setStudentInformation(StudentInformation studentInformation) {
        this.studentInformation = studentInformation;
    }
}
