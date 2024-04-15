package com.example.demo.repo;

import com.example.demo.model.fake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface fakeRepo extends JpaRepository<fake,String> {
}
