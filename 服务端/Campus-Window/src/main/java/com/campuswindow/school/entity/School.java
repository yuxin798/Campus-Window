package com.campuswindow.school.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_school")
@Data
public class School {
    @Id
    private int schoolId;
    private String schoolName;
    private String schoolLogo;
}
