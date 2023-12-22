package com.campuswindow.activity.commentimage.repository;

import com.campuswindow.activity.commentimage.entity.CommentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentImageRepository extends JpaRepository<CommentImage, String> {

    /*
     * 根据评论id查询图片
     */
    @Query(value = "select * from tbl_comment_image where comment_id = ?1",nativeQuery = true)
    List<CommentImage> findCommentImageByCommentId(String commentId);

    /*
     * 根据评论id删除图片
     */
    @Query(value = "delete from tbl_comment_image where comment_id = ?1 ", nativeQuery = true)
    @Modifying
    void deleteCommentImageByCommentId(String commentId);
}
