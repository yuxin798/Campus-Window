package com.campuswindow.activity.commentimage.service;

import com.campuswindow.activity.commentimage.entity.CommentImage;
import com.campuswindow.activity.commentimage.repository.CommentImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentImageService {

    private CommentImageRepository commentImageRepository;

    @Autowired
    public void setCommentImageRepository(CommentImageRepository commentImageRepository) {
        this.commentImageRepository = commentImageRepository;
    }

    public void saveCommentImage(CommentImage commentImage) {
        commentImageRepository.save(commentImage);
    }

    /*
     * 用户发表评论后，根据UserId 更改 tbl_activity_image 表中的commentId
     */
    public void updateCommentIdByUserId(String commentId, String userId){
        commentImageRepository.updateCommentIdByUserId(commentId, userId);
    }

    /*
     * 用户点击返回按键表明不再发帖，删除 tbl_activity_image 表中的无效的图片数据
     */

    public void deleteCommentImageByUserId(String userId) {
        commentImageRepository.deleteCommentImageByUserId(userId);
    }

    /*
     * 删帖时同时删除 tbl_activity_image 表中的图片数据
     */
    public void deleteCommentImageByCommentId(String commentId){
        commentImageRepository.deleteCommentImageByCommentId(commentId);
    }
}
