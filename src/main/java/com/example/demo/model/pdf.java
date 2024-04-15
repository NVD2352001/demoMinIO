package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class pdf {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String image;
        private int imageId;
        private String pdf_name;
        private String name;
        private String nv;
        private String sex;
        private String ns;
        private String cccd;
        private Date nc;
        private String here;
        private String bhyt;
        private String phone_number;
        private String adress;
        private String job;
        private String work_place;
        private String date_start_work;
        private String previous_work1;
        private String year1;
        private String month1;
        private Date since1;
        private Date to1;
        private String previous_work2;
        private String year2;
        private String month2;
        private Date since2;
        private Date to2;
        private String family_illness;

    }
