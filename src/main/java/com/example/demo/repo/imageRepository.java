package com.example.demo.repo;

import com.example.demo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface imageRepository extends JpaRepository<Image,Integer> {
}

