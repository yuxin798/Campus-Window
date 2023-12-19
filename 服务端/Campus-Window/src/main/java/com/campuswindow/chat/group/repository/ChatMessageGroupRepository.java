package com.campuswindow.chat.group.repository;

import com.campuswindow.chat.group.entity.ChatMessageGroup;
import com.campuswindow.chat.group.vo.ChatMessageGroupVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageGroupRepository extends JpaRepository<ChatMessageGroup, String> {

    @Query(value = "select new com.campuswindow.chat.group.vo.ChatMessageGroupVo(c.messageId, c.linkId, c.userId, u.userName, u.avatar, c.content, c.sendTime, c.type) from ChatMessageGroup as c join User as u on c.userId = u.userId where c.linkId = ?1 order by c.sendTime asc ")
    List<ChatMessageGroupVo> findAll(String linkId);
}
