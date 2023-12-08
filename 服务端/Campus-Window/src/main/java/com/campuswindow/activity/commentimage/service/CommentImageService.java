package com.campuswindow.activity.commentimage.service;

import com.campuswindow.activity.commentimage.entity.CommentImage;
import com.campuswindow.activity.commentimage.repository.CommentImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CommentImageService {

    private CommentImageRepository commentImageRepository;

    /*
     * 根据评论Id删除评论图片或视频
     * 用点：删评论时同时删除 tbl_activity_image 表中的图片数据
     */
    public void deleteCommentImageByCommentId(String commentId){
        commentImageRepository.deleteCommentImageByCommentId(commentId);
    }

    /*
     * 保存评论图片或视频 type 0 评论图片 1 评论视频
     */
    public void save(List<String> images, String contentId, String userId, int type){
        for(String image : images){
            String imageId = UUID.randomUUID().toString().replace("-", "");
            CommentImage commentImage = new CommentImage(imageId, contentId, userId, image, type);
            commentImageRepository.save(commentImage);
        }
    }

    /*
     * 根据评论Id查询评论图片或视频
     */
    public List<CommentImage> findCommentImageByCommentId(String commentId){
        return commentImageRepository.findCommentImageByCommentId(commentId);
    }

    @Autowired
    public void setCommentImageRepository(CommentImageRepository commentImageRepository) {
        this.commentImageRepository = commentImageRepository;
    }
}
