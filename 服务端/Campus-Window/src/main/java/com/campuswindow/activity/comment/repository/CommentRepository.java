package com.campuswindow.activity.comment.repository;

import com.campuswindow.activity.comment.entity.Comment;
import com.campuswindow.activity.comment.vo.CommentVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

//    @Query(value = "select ", nativeQuery = true)
//    void findAllByUserId(String userId);

    @Query(value = "select new com.campuswindow.activity.comment.vo.CommentVo(c.commentId, c.activityId, c.userId, c.content, c.love, c.sendTime, u.userName, u.avatar) from Comment as c join User as u on c.userId = u.userId where c.activityId = ?1")
    List<CommentVo> findAllByActivityId(String activityId);


    @Query(value = "update tbl_comment set love = love + ?2 where comment_id = ?1", nativeQuery = true)
    @Modifying
    void updateLove(String commentId, int i);
}
