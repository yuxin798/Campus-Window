package com.campuswindow.chat.repository;

import com.campuswindow.chat.dto.ChatListDto;
import com.campuswindow.chat.entity.ChatList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatListRepository extends JpaRepository<ChatList, String> {
    List<ChatList> findChatListByFromUserId(String fromUserId);

    Page<ChatList> findAllByFromUserId(Pageable pageable, String fromUserId);

    @Query(value = "select from_window + to_window from chat_list where link_id =?1 and from_user_id =?2 and to_user_id =?3", nativeQuery = true)
    Integer selectIsSaveWindows(String linkId, String fromUserId, String toUserId);

    @Query(value = "update chat_list set unread = unread + 1 where link_id =?1 and from_user_id =?3 and to_user_id =?2", nativeQuery = true)
    @Modifying
    void updateUnread(String linkId, String fromUserId, String toUserId);

    @Query(value = "update chat_list set unread = 0 where link_id =?1 and from_user_id =?2 and to_user_id =?3", nativeQuery = true)
    @Modifying
    void clearUnread(String linkId, String fromUserId, String toUserId);

    @Query(value = "update chat_list set from_window = ?3 where link_id = ?1 and from_user_id =?2", nativeQuery = true)
    @Modifying
    void updateFromWindows(String linkId, String fromUserId, int flag);

    @Query(value = "update chat_list set to_window = ?3 where link_id = ?1 and to_user_id =?2", nativeQuery = true)
    @Modifying
    void updateToWindows(String linkId, String toUserId, int flag);

    @Query(value = "update chat_list set from_window = false where link_id != ?1 and from_user_id =?2",nativeQuery = true)
    @Modifying
    void updateOtherWindows(String linkId, String fromUserId);

    @Query(value = "update chat_list set last_msg =?2 where link_id =?1", nativeQuery = true)
    @Modifying
    void updateLastMsg(String linkId, String content);

    @Query(value = "select new com.campuswindow.chat.dto.ChatListDto(c.listId, c.linkId, c.fromUserId, c.toUserId,  u.userName, u.avatar, c.fromWindow, c.toWindow, c.lastMsg, c.unread, c.status) from ChatList as c join User as u on c.toUserId = u.userId where c.fromUserId = ?1 and (c.status = 1 or c.unread != 0)")
    List<ChatListDto> findAllByFromUserId(String fromUserId);

    @Query(value = "update chat_list set status = ?3 where from_user_id = ?1 and to_user_id = ?2",nativeQuery = true)
    @Modifying
    void updateChatListStatus(String fromUserId, String toUserId, boolean flag);
}
