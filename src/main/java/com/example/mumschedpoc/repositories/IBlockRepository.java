package com.example.mumschedpoc.repositories;

import com.example.mumschedpoc.entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlockRepository extends JpaRepository<Block, Integer> {
}
