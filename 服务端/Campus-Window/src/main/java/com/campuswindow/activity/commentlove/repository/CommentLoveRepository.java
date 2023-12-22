package com.campuswindow.activity.commentlove.repository;

import com.campuswindow.activity.commentlove.entity.CommentLove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentLoveRepository extends JpaRepository<CommentLove, String> {

    @Query(value = "select count(*) from CommentLove where userId =?1 and commentId =?2")
    int findByUserIdAndActivityId(String userId, String commentId);

    @Query(value = "delete from CommentLove where userId =?1 and commentId =?2")
    @Modifying
    void deleteByUserIdAndActivityId(String userId, String commentId);
}
