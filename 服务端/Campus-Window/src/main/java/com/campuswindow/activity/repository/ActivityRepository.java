package com.campuswindow.activity.repository;

import com.campuswindow.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, String> {

    List<Activity> findActivityByUserId(String userId);
}
