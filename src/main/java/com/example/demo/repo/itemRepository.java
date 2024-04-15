package com.example.demo.repo;

import com.example.demo.model.item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface itemRepository extends JpaRepository<item,Integer> {
   // Optional<item>
}
