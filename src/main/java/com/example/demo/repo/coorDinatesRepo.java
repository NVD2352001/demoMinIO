package com.example.demo.repo;

import com.example.demo.model.coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface coorDinatesRepo extends JpaRepository<coordinates,String> {
    List<coordinates> findByType(String type);
}
