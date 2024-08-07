package com.campuswindow.activity.comment.repository;

import com.campuswindow.activity.comment.entity.Comment;
import com.campuswindow.activity.comment.vo.CommentUserVo;
import com.campuswindow.activity.comment.vo.CommentVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

//    @Query(value = "select ", nativeQuery = true)
//    void findAllByUserId(String userId);

    /*
     * 根据帖子Id查询所有评论，先根据点赞数降序排序，再根据发帖时间降序排序
     */
    @Query(value = "select new com.campuswindow.activity.comment.vo.CommentVo(c.commentId, c.activityId, c.userId, c.content, c.love, c.sendTime, u.userName, u.avatar) from Comment as c join User as u on c.userId = u.userId where c.activityId = ?1 and c.parentId = null order by c.love desc, c.sendTime desc ")
    List<CommentVo> findAllByActivityId(String activityId);

    /*
     * 根据帖子Id，变更点赞数  i为1表示增加，i为-1表示减少
     */
    @Query(value = "update tbl_comment set love = love + ?2 where comment_id = ?1", nativeQuery = true)
    @Modifying
    void updateLove(String commentId, int i);

    @Query(value = "select new com.campuswindow.activity.comment.vo.CommentUserVo(c.commentId, c.activityId, a.activityTitle, c.content, c.love, c.sendTime) from Comment as c join Activity as a on c.activityId = a.activityId where c.userId = ?1 order by c.love desc, c.sendTime desc ")
    List<CommentUserVo> findAllByUserId(String userId);

//    @Query(value = "select new com.campuswindow.activity.comment.vo.CommentVo(c.commentId, c.activityId, c.userId, c.content, c.love, c.sendTime, u.userName, u.avatar, m.userId, m.userName, m.avatar) " +
//            "from Comment as c " +
//            "join User as u on c.userId = u.userId " +
//            "join User as m on c.toUserId = m.userId " +
//            "join Comment as a on c.parentId = a.commentId " +
//            "where a.parentId = ?1 or c.parentId = ?1")
//    List<CommentVo> findAllByParentId(String commentId);

    @Query(value = "select new com.campuswindow.activity.comment.vo.CommentVo(c.commentId, c.activityId, c.userId, c.content, c.love, c.sendTime, u.userName, u.avatar, m.userId, m.userName) " +
            "from Comment as c " +
            "join User as u on c.userId = u.userId " +
            "join User as m on c.toUserId = m.userId " +
            "join Comment as a on c.parentId = a.commentId " +
            "where a.parentId = ?1 or c.parentId = ?1")
    List<CommentVo> findAllByParentId(String commentId);

    @Query(value = "select count(*) from Comment as c join Comment as a on c.parentId = a.commentId  where c.parentId = ?1 or a.parentId = ?1")
    int findReplyCountByParentId(String commentId);
}
