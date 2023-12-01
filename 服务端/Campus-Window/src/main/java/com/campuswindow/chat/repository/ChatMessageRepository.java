package com.campuswindow.chat.repository;

import com.campuswindow.chat.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String>, JpaSpecificationExecutor<ChatMessage> {

    Page<ChatMessage> findAllByFromUserIdAndToUserId(Pageable pageable, String fromUserId, String toUserId);

    @Query(value = "select content from chat_message where from_user_Id = ?1 and to_user_id = ?2", nativeQuery = true)
    String findLastContentByFromUserIdAndToUserId(String fromUserId, String toUserId);

    @Query(value = "update chat_message set is_latest = false where link_id = ?1", nativeQuery = true)
    @Modifying
    void updateMessageStatus(String linkId);
}
