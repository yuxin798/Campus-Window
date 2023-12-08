package com.campuswindow.activity.comment.service;

import com.campuswindow.activity.comment.dto.CommentDto;
import com.campuswindow.activity.comment.entity.Comment;
import com.campuswindow.activity.comment.repository.CommentRepository;
import com.campuswindow.activity.comment.vo.CommentVo;
import com.campuswindow.activity.commentimage.service.CommentImageService;
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
    private CommentImageService commentImageService;

    /*
     * 发表评论
     */
    public void addComment(CommentDto commentDto){
        String commentId = UUID.randomUUID().toString().replace("-", "");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(commentId, commentDto.getActivityId(), commentDto.getUserId(), commentDto.getContent(), 0, timestamp);
        commentRepository.save(comment);
        commentImageService.save(commentDto.getImages(), commentId, commentDto.getUserId(), 0);
        commentImageService.save(commentDto.getVideos(), commentId, commentDto.getUserId(), 1);
    }

    /*
     * 根据评论Id删除评论
     */
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
        commentImageService.deleteCommentImageByCommentId(commentId);
    }

    /*
     * 根据帖子Id查询所有评论，包含图片和视频，并根据点赞数和发表事件降序排序
     */
    public List<CommentVo> findAllCommentsByActivityId(String activityId) {
        return commentRepository.findAllByActivityId(activityId)
                .stream().peek(e -> e.setCommentImages(commentImageService.findCommentImageByCommentId(e.getCommentId()))).collect(Collectors.toList());
    }

    /*
     * 点赞
     */
    public void addLove(String commentId) {
        commentRepository.updateLove(commentId, 1);
    }

    /*
     * 取消点赞
     */
    public void decreaseLove(String commentId) {
        commentRepository.updateLove(commentId, -1);
    }

    @Autowired
    public void setCommentImageService(CommentImageService commentImageService) {
        this.commentImageService = commentImageService;
    }
    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
