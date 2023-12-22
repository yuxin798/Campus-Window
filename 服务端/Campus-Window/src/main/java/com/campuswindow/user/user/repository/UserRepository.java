package com.campuswindow.user.user.repository;

import com.campuswindow.user.user.entity.User;
import com.campuswindow.user.user.vo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByEmail(String email);

    @Modifying
    @Query("update User set avatar = ?2 where userId = ?1")
    void updateAvatarByUserId(String userId, String avatar);

    @Modifying
    @Query("update User set background = ?2 where userId = ?1")
    void updateBackground(String userId, String background);

    User findChatUserByUserId(String userId);

    @Modifying
    @Query("update User set password = ?2 where email = ?1")
    void updatePasswordByUserId(String email, String password);

    User findLoginDtoByEmail(String email);

    @Query("update User set userName = ?2, gender = ?3, signature = ?4 where userId = ?1")
    @Modifying
    void updateInformationByUserId(String userId, String userName, int gender, String signature);

    @Query("select new com.campuswindow.user.user.vo.ModifyInformationVo(userId, userName, gender, signature,avatar) from User where userId = ?1")
    ModifyInformationVo findInformation(String userId);

    @Query(value = "select school from User where userId = ?1")
    String findSchoolByUserId(String userId);

    @Query(value = "update User set followers = followers + ?2 where userId = ?1")
    @Modifying
    void updateFollowersByUserId(String userId, int i);

    @Query(value = "update User set fans = fans + ?2 where userId = ?1")
    @Modifying
    void updateFansByUserId(String toUserId, int i);

    @Query(value = "update User set loves = loves + ?2 where userId = ?1")
    @Modifying
    void updateLovesByUserId(String userId, int i);

    @Query(value = "update User set friends = friends + ?2 where userId = ?1")
    @Modifying
    void updateFriendsByUserIdAndToUserId(String userId, int i);

    @Query(value = "select new com.campuswindow.user.user.vo.UserVo(userId, userName, school, avatar, gender, signature, loves, friends, followers, fans, background) from User where userId =?1")
    UserVo findUserByUserId(String userId);

    @Query(value = "select new com.campuswindow.user.user.vo.FriendsVo(u.userId, u.userName, u.avatar, u.signature) from User as u join Follow as f1 on u.userId = f1.toUserId join Follow as f2 on u.userId = f2.userId where f1.userId = ?1 and f2.toUserId = ?1")
    List<FriendsVo> findFriendsByUserId(String userId);

    @Query(value = "select new com.campuswindow.user.user.vo.FollowersVo(u.userId, u.userName, u.avatar, u.signature) from User as u join Follow as f on u.userId = f.toUserId where f.userId = ?1")
    List<FollowersVo> findFollowersByUserId(String userId);

    @Query(value = "select new com.campuswindow.user.user.vo.FansVo(u.userId, u.userName, u.avatar, u.signature) from User as u join Follow as f on u.userId = f.userId where f.toUserId = ?1")
    List<FansVo> findFansByUserId(String userId);

    @Query(value = "select new com.campuswindow.user.user.vo.OtherUserVo(userId, userName, school, avatar, gender, signature, loves, friends, followers, fans, background) from User where userId =?1")
    OtherUserVo findUserByToUserId(String toUserId);

    @Query(value = "select userName from User where userId = ?1")
    String findUserNameByUserId(String userId);

    @Query(value = "select new com.campuswindow.user.user.vo.FriendsVo(u.userId, u.userName, u.avatar, u.signature) from User as u join Follow as f1 on u.userId = f1.toUserId join Follow as f2 on u.userId = f2.userId where f1.userId = ?1 and f2.toUserId = ?1 and u.userName like %?2%")
    List<FriendsVo> findFriendsByUserName(String userId, String userName);

    @Query(value = "select new com.campuswindow.user.user.vo.FollowersVo(u.userId, u.userName, u.avatar, u.signature) from User as u join Follow as f on u.userId = f.toUserId where f.userId = ?1 and u.userName like %?2%")
    List<FollowersVo> findFollowersByUserName(String userId, String userName);

    @Query(value = "select new com.campuswindow.user.user.vo.FansVo(u.userId, u.userName, u.avatar, u.signature) from User as u join Follow as f on u.userId = f.userId where f.toUserId = ?1 and u.userName like %?2%")
    List<FansVo> findFansByUserName(String userId, String userName);
}
