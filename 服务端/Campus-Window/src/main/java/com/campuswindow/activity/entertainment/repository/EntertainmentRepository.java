package com.campuswindow.activity.entertainment.repository;

import com.campuswindow.activity.entertainment.entity.EntertainmentActivity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntertainmentRepository extends JpaRepository<EntertainmentActivity, String> {

    List<EntertainmentActivity> findActivityByUserId(String userId, Sort sort);
}
