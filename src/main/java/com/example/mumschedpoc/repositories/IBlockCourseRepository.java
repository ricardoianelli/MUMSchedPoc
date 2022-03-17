package com.example.mumschedpoc.repositories;

import com.example.mumschedpoc.entities.BlockCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlockCourseRepository extends JpaRepository<BlockCourse, Integer> {
}
