package com.campuswindow.user.repository;

import com.campuswindow.user.entity.User;
import com.campuswindow.user.vo.ModifyInformationVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByEmail(String email);

    @Modifying
    @Query("update User set avatar = ?2 where userId = ?1")
    int updateAvatarByUserId(String userId, String avatar);

    User findChatUserByUserId(String userId);

    @Modifying
    @Query("update User set password = ?2 where email = ?1")
    void updatePasswordByUserId(String email, String password);

    User findLoginDtoByEmail(String email);


    User findUserNameAndAvatarByUserId(String userId);

    User findUserNameAndAvatarAndSchoolByUserId(String userId);

    @Query("update User set userName = ?2, gender = ?3, signature = ?4 where userId = ?1")
    @Modifying
    void updateInformationByUserId(String userId, String userName, int gender, String signature);

    @Query("select new com.campuswindow.user.vo.ModifyInformationVo(userId, userName, gender, signature,avatar) from User where userId = ?1")
    ModifyInformationVo findInformation(String userId);

    @Query(value = "select school from User where userId = ?1")
    String findSchoolByUserId(String userId);
}
