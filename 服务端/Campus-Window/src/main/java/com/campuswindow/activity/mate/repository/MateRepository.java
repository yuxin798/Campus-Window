package com.campuswindow.activity.mate.repository;

import com.campuswindow.activity.mate.entity.MateActivity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateRepository extends JpaRepository<MateActivity, String> {

    List<MateActivity> findActivityByUserId(String userId, Sort sort);
}
