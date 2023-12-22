package com.campuswindow.activity.commentlove.service;

import com.campuswindow.activity.commentlove.entity.CommentLove;
import com.campuswindow.activity.commentlove.repository.CommentLoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentLoveService {
    private CommentLoveRepository commentLoveRepository;

    @Autowired
    public void setCommentLoveRepository(CommentLoveRepository commentLoveRepository) {
        this.commentLoveRepository = commentLoveRepository;
    }

    public boolean findCommentLoveByUserIdAndCommentId(String userId, String commentId) {
        if (commentLoveRepository.findByUserIdAndActivityId(userId, commentId) == 0) {
            return false;
        }
        return true;
    }

    public void addLove(CommentLove commentLove) {
        String activityLoveId = UUID.randomUUID().toString().replace("-", "");
        commentLove.setLoveId(activityLoveId);
        commentLoveRepository.save(commentLove);
    }

    public void decreaseLove(CommentLove commentLove) {
        commentLoveRepository.deleteByUserIdAndActivityId(commentLove.getUserId(), commentLove.getCommentId());
    }
}
