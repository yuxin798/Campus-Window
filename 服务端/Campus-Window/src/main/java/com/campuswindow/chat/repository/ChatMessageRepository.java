package com.campuswindow.chat.repository;

import com.campuswindow.chat.entity.ChatMessage;
import com.campuswindow.chat.vo.ChatMessageVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    @Query(value = "select new com.campuswindow.chat.vo.ChatMessageVo(c.messageId, c.linkId, c.userId, u.userName, u.avatar, c.content, c.sendTime, c.type) from ChatMessage as c join User as u on c.userId = u.userId where c.linkId = ?1 order by c.sendTime asc ")
    List<ChatMessageVo> findAll(String linkId);

    @Query(value = "select count(*) from ChatMessage where linkId = ?1")
    int findUnreadByLinkId(String linkId);

    @Query(value = "delete from ChatMessage where linkId = ?1")
    @Modifying
    void deleteAllByLinkId(String linkId);
}
