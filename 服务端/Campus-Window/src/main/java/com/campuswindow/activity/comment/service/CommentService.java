package com.campuswindow.activity.comment.service;

import com.campuswindow.activity.comment.dto.CommentDto;
import com.campuswindow.activity.comment.entity.Comment;
import com.campuswindow.activity.comment.repository.CommentRepository;
import com.campuswindow.activity.comment.vo.CommentVo;
import com.campuswindow.activity.commentimage.repository.CommentImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {
    private CommentRepository commentRepository;
    private CommentImageRepository commentImageRepository;

    @Autowired
    public void setCommentImageRepository(CommentImageRepository commentImageRepository) {
        this.commentImageRepository = commentImageRepository;
    }
    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment(CommentDto commentDto){
        String commentId = UUID.randomUUID().toString().replace("-", "");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(commentId, commentDto.getActivityId(), commentDto.getUserId(), commentDto.getContent(), 0, timestamp);
        commentRepository.save(comment);
        commentImageRepository.updateCommentIdByUserId(commentId, commentDto.getUserId());
    }


    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<CommentVo> findAllCommentsByActivityId(String activityId) {
        return commentRepository.findAllByActivityId(activityId)
                .stream().peek(e -> e.setCommentImages(commentImageRepository.findCommentImageByCommentId(e.getCommentId()))).collect(Collectors.toList());
    }


    public void addLove(String commentId) {
        commentRepository.updateLove(commentId, 1);
    }

    public void decreaseLove(String commentId) {
        commentRepository.updateLove(commentId, -1);
    }
}
