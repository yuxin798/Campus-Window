package com.campuswindow.activity.commentimage.repository;

import com.campuswindow.activity.commentimage.entity.CommentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentImageRepository extends JpaRepository<CommentImage, String> {
    @Query(value = "select image from tbl_comment_image where comment_id = ?1",nativeQuery = true)
    List<String> findCommentImageByCommentId(String commentId);

    @Query(value = "delete from tbl_comment_image where comment_id = ?1 ", nativeQuery = true)
    @Modifying
    void deleteCommentImageByCommentId(String commentId);
}
