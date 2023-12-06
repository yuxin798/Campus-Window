package com.campuswindow.activity.comment.service;

import com.campuswindow.activity.comment.dto.CommentDto;
import com.campuswindow.activity.comment.entity.Comment;
import com.campuswindow.activity.comment.repository.CommentRepository;
import com.campuswindow.activity.comment.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment(CommentDto commentDto){
        String commentId = UUID.randomUUID().toString().replace("-", "");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(commentId, commentDto.getActivityId(), commentDto.getUserId(), commentDto.getContent(), 0, timestamp);
        commentRepository.save(comment);
    }


    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }

//    public void findAllComments(String userId) {
//        commentRepository.findAllByUserId(userId);
//    }

    public List<CommentVo> findAllCommentsByActivityId(String activityId) {
//        List<String> images = findActivityImageByActivityId(activityId);
        return commentRepository.findAllByActivityId(activityId);
    }


    public void addLove(String commentId) {
        commentRepository.updateLove(commentId, 1);
    }

    public void decreaseLove(String commentId) {
        commentRepository.updateLove(commentId, -1);
    }
}
