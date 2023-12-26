package com.campuswindow.school.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_school")
@Data
@Schema(description = "学校")
public class School {
    @Id
    @Schema(description = "学校Id")
    private int schoolId;
    @Schema(description = "学校名称")
    private String schoolName;
    @Schema(description = "校徽")
    private String schoolLogo;
}
