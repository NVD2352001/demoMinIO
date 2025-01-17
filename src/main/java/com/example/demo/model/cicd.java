package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class cicd {
    @Id
    private String uuid;
    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
    private String type;
    private String nxy;
}
