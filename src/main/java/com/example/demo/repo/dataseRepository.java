package com.example.demo.repo;

import com.example.demo.model.datase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface dataseRepository extends JpaRepository<datase,Integer> {
       List<datase> findByItemId (int id);
}
