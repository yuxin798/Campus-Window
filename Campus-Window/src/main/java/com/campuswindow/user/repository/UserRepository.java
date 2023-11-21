package com.practicaltraining.user.repository;

import com.practicaltraining.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUserName(String userName);

    @Modifying
    @Query("update User set avatar = ?2 where userId = ?1")
    int updateAvatarByUserId(String userId, String avatar);

    @Modifying
    @Query("update User set password = ?2 where userId = ?1")
    int updatePasswordByUserId(String userId, String password);
}
