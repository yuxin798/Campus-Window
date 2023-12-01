package com.campuswindow.chat.repository;

import com.campuswindow.chat.entity.ChatLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatLinkRepository extends JpaRepository<ChatLink, String> {
    @Query(value = "select link_id from chat_user_link where from_user_Id = ?1 and to_user_id = ?2", nativeQuery = true)
    String findLinkIdByFromUserIdAndToUserId(String fromUserId, String toUserId);

}
