package com.campuswindow.user.repository;

import com.campuswindow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByEmail(String email);

    @Modifying
    @Query("update User set avatar = ?2 where userId = ?1")
    int updateAvatarByUserId(String userId, String avatar);


    @Modifying
    @Query("update User set password = ?2 where email = ?1")
    int updatePasswordByEmail(String email, String password);
}
