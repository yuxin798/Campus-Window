package com.campuswindow.school.repository;

import com.campuswindow.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SchoolRepository extends JpaRepository<School, Integer>, JpaSpecificationExecutor<School> {

    School findSchoolBySchoolName(String schoolName);
}
