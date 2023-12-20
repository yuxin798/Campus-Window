package com.campuswindow.user.follow.repository;

import com.campuswindow.user.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, String> {

    @Query(value = "delete from Follow where userId = ?1 and toUserId = ?2")
    @Modifying
    void deleteByUserIdAndToUserId(String userId, String toUserId);

    @Query(value = "select count(*) from Follow where (userId = ?1 and toUserId = ?2) or (userId = ?2 and toUserId = ?1)")
    int findCountByUserIdAndToUserId(String userId, String toUserId);

    @Query(value = "select count(*) from Follow where userId = ?1 and toUserId = ?2")
    int findFollowByUserIdAndToUserId(String userId, String toUserId);
}
