package com.campuswindow.school.service;

import com.campuswindow.school.entity.School;
import com.campuswindow.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository repository;

    public List<School> findAll() {
        Sort.Order order = Sort.Order.asc("schoolName");
        Sort sort = Sort.by(order);
        return repository.findAll(sort);
    }

    public School findOne(String schoolName) {
        return repository.findSchoolBySchoolName(schoolName);
    }

//    public School findOne(String userId) {
//        return repository.findSchoolById(userId);
//    }
}
