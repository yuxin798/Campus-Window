package com.campuswindow.activity.comment.controller;

import com.campuswindow.activity.comment.dto.CommentDto;
import com.campuswindow.activity.comment.service.CommentService;
import com.campuswindow.activity.comment.vo.CommentUserVo;
import com.campuswindow.activity.comment.vo.CommentVo;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity/comment")
@Tag(name = "评论接口")
public class CommentController {

    private CommentService commentService;

    /*
     * 发表评论，同时将上传的图片或视频保存到数据库
     */
    @GetMapping("/addComment")
    @Operation(summary = "发表评论")
    public Result<?> addComment(@RequestBody CommentDto commentDto){
        commentService.addComment(commentDto);
        return ResultVOUtil.success();
    }

    /*
     * 根据评论Id删除评论，同时删除该评论所有的视频或图片
     */
    @GetMapping("/deleteComment")
    @Operation(summary = "删除评论")
    public Result<?> deleteComment(String commentId, String activityId){
        commentService.deleteComment(commentId, activityId);
        return ResultVOUtil.success();
    }

    /*
     * 根据帖子Id查询所有评论
     */
    @GetMapping("findAllCommentsByActivityId")
    @Operation(summary = "根据帖子Id查询所有评论")
    public Result<List<CommentVo>> findAllCommentsByActivityId(String activityId){
        List<CommentVo> comments= commentService.findAllCommentsByActivityId(activityId);
        return ResultVOUtil.success(comments);
    }

    /*
     * 点赞
     */
    @GetMapping("/addLove")
    @Operation(summary = "点赞")
    public Result<?> addLove(String commentId){
        commentService.addLove(commentId);
        return ResultVOUtil.success();
    }

    /*
     * 取消点赞
     */
    @GetMapping("/decreaseLove")
    @Operation(summary = "取消点赞")
    public Result<?> decreaseLove(String commentId){
        commentService.decreaseLove(commentId);
        return ResultVOUtil.success();
    }

    @GetMapping("findAllComments")
    @Operation(summary = "根据用户Id查询所有评论")
    public Result<List<CommentUserVo>> findAllComments(String userId){
        List<CommentUserVo> commentUserVos = commentService.findAllComments(userId);
        return ResultVOUtil.success(commentUserVos);
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

}
