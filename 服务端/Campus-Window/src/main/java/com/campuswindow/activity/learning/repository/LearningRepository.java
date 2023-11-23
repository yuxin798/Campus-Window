package com.campuswindow.activity.learning.repository;

import com.campuswindow.activity.learning.entity.LearningActivity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningRepository extends JpaRepository<LearningActivity, String> {

    List<LearningActivity> findActivityByUserId(String userId, Sort sort);
}
