package com.example.demo.service;

import com.example.demo.model.coordinates;
import com.example.demo.repo.coorDinatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class coorDinatesSevice {
    @Autowired
    private coorDinatesRepo repo;
    public coordinates adds( coordinates add){
        return repo.save(add);
    }

}
