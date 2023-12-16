package com.campuswindow.activity.comment.service;

import com.campuswindow.activity.activity.service.ActivityService;
import com.campuswindow.activity.comment.dto.CommentDto;
import com.campuswindow.activity.comment.dto.ReplyCommentDto;
import com.campuswindow.activity.comment.entity.Comment;
import com.campuswindow.activity.comment.repository.CommentRepository;
import com.campuswindow.activity.comment.vo.CommentUserVo;
import com.campuswindow.activity.comment.vo.CommentVo;
import com.campuswindow.activity.commentimage.service.CommentImageService;
import com.campuswindow.activity.commentlove.entity.CommentLove;
import com.campuswindow.activity.commentlove.service.CommentLoveService;
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
    private ActivityService activityService;
    private CommentLoveService commentLoveService;
    /*
     * 发表评论，同时将图片或视频网络地址保存到数据库中
     */
    public void addComment(CommentDto commentDto){
        String commentId = UUID.randomUUID().toString().replace("-", "");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(commentId, commentDto.getActivityId(), commentDto.getUserId(), commentDto.getContent(), 0, timestamp, null, null);
        commentRepository.save(comment);
        commentImageService.save(commentDto.getImages(), commentId, commentDto.getUserId(), 0);
        commentImageService.save(commentDto.getVideos(), commentId, commentDto.getUserId(), 1);
        activityService.addComment(commentDto.getActivityId());
    }

    /*
     * 回复评论，同时将图片或视频网络地址保存到数据库中
     */
    public void addReplyComment(ReplyCommentDto replyCommentDto) {
        String commentId = UUID.randomUUID().toString().replace("-", "");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(commentId, replyCommentDto.getActivityId(), replyCommentDto.getUserId(), replyCommentDto.getContent(), 0, timestamp, replyCommentDto.getParentId(), replyCommentDto.getToUserId());
        commentRepository.save(comment);
        commentImageService.save(replyCommentDto.getImages(), commentId, replyCommentDto.getUserId(), 0);
        commentImageService.save(replyCommentDto.getVideos(), commentId, replyCommentDto.getUserId(), 1);
    }

    /*
     * 根据评论Id删除评论
     */
    public void deleteComment(String commentId, String activityId) {
        commentRepository.deleteById(commentId);
        commentImageService.deleteCommentImageByCommentId(commentId);
        activityService.decreaseComment(activityId);
    }

    /*
     * 根据帖子Id查询所有评论，包含图片和视频网络地址，并根据点赞数和发表事件降序排序
     */
    public List<CommentVo> findAllCommentsByActivityId(String userId, String activityId) {
        return commentRepository.findAllByActivityId(activityId)
                .stream()
                .peek(e -> e.setReplyCount(findReplyCountByParentId(e.getCommentId())))
//                .peek(e -> e.setReplyComments(commentRepository.findAllByParentId(e.getCommentId())))
                .peek(e -> e.setLoved(commentLoveService.findCommentLoveByUserIdAndCommentId(userId, e.getCommentId())))
                .peek(e -> e.setCommentImages(commentImageService.findCommentImageByCommentId(e.getCommentId()))).collect(Collectors.toList());
    }

    /*
     * 查询回复评论数
     */
    private int findReplyCountByParentId(String commentId) {
        return commentRepository.findReplyCountByParentId(commentId);
    }

    /*
     * 根据评论Id查询所有回复评论，包含图片和视频网络地址，并根据点赞数和发表事件降序排序
     */
    public List<CommentVo> findAllCommentsByCommentId(String userId, String commentId) {
        return commentRepository.findAllByParentId(commentId)
               .stream()
               .peek(e -> e.setLoved(commentLoveService.findCommentLoveByUserIdAndCommentId(userId, e.getCommentId())))
               .peek(e -> e.setCommentImages(commentImageService.findCommentImageByCommentId(e.getCommentId()))).collect(Collectors.toList());
    }


    /*
     * 点赞
     */
    public void addLove(CommentLove commentLove) {
        commentRepository.updateLove(commentLove.getCommentId(), 1);
        commentLoveService.addLove(commentLove);
    }

    /*
     * 取消点赞
     */
    public void decreaseLove(CommentLove commentLove) {
        commentRepository.updateLove(commentLove.getCommentId(), -1);
        commentLoveService.decreaseLove(commentLove);
    }

    public List<CommentUserVo> findAllComments(String userId) {
        return commentRepository.findAllByUserId(userId)
                .stream().peek(e -> e.setCommentImages(commentImageService.findCommentImageByCommentId(e.getCommentId()))).collect(Collectors.toList());
    }

    @Autowired
    public void setCommentImageService(CommentImageService commentImageService) {
        this.commentImageService = commentImageService;
    }
    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }
    @Autowired
    public void setCommentLoveService(CommentLoveService commentLoveService) {
        this.commentLoveService = commentLoveService;
    }

}
