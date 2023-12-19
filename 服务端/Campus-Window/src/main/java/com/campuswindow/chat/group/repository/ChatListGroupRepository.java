package com.campuswindow.chat.group.repository;

import com.campuswindow.chat.group.entity.ChatListGroup;
import com.campuswindow.chat.group.vo.ChatListVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ChatListGroupRepository extends JpaRepository<ChatListGroup, String> {
    @Query(value = "update chat_list_group set window = ?3 where link_id = ?1 and user_id =?2", nativeQuery = true)
    @Modifying
    void updateWindows(String linkId, String userId, int i);

    @Query(value = "update chat_list_group set unread = 0 where link_id =?1 and user_id =?2", nativeQuery = true)
    @Modifying
    void clearUnread(String linkId, String userId);

    @Query(value = "update ChatListGroup set lastMsg =?2, lastMsgTime = ?3 where linkId =?1")
    @Modifying
    void updateLastMsgAndTime(String linkId, String content, Timestamp sendTime);

    @Query(value = "select window from chat_list_group where link_id =?1 and user_id =?2", nativeQuery = true)
    int selectIsSaveWindows(String linkId, String userId);

    @Query(value = "update chat_list_group set unread = unread + 1 where link_id =?1 and user_id =?2", nativeQuery = true)
    @Modifying
    void updateUnread(String linkId, String userId);

    @Query(value = "update chat_list_group set window = ?2 where user_id =?1", nativeQuery = true)
    @Modifying
    void updateWindows(String userId, int i);

    @Query(value = "update chat_list_group set status = ?3 where link_id = ?1 and user_id = ?2",nativeQuery = true)
    @Modifying
    void updateChatListStatus(String linkId, String userId, boolean b);

    @Query(value = "select new com.campuswindow.chat.group.vo.ChatListVo(c.listId, c.linkId, " +
            "case l.type when 1 then l.name when 0 then (select u.userName from ChatListGroup as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end," +
            "case l.type when 1 then l.avatar when 0 then (select u.avatar from ChatListGroup as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end," +
            "l.num, c.lastMsg, c.lastMsgTime, c.unread, c.status, l.type) " +
            "from ChatListGroup as c join ChatLinkGroup as l on c.linkId = l.linkId " +
            "where c.userId = ?1 and (c.status = 1 or c.unread != 0) order by c.lastMsgTime desc")
    List<ChatListVo> findAllByFromUserId(String userId);

    @Query("select userId from ChatListGroup where linkId = ?1")
    List<String> findUserIdByLinkId(String linkId);

}
