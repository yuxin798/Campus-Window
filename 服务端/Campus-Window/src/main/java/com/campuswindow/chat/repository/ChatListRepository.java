package com.campuswindow.chat.repository;

import com.campuswindow.chat.entity.ChatList;
import com.campuswindow.chat.vo.ChatListFollowVo;
import com.campuswindow.chat.vo.ChatListVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ChatListRepository extends JpaRepository<ChatList, String> {
    @Query(value = "update chat_list set window = ?3 where link_id = ?1 and user_id =?2", nativeQuery = true)
    @Modifying
    void updateWindows(String linkId, String userId, int i);

    @Query(value = "update chat_list set unread = 0 where link_id =?1 and user_id =?2", nativeQuery = true)
    @Modifying
    void clearUnread(String linkId, String userId);

    @Query(value = "update ChatList set lastMsg =?2, lastMsgTime = ?3 where linkId =?1")
    @Modifying
    void updateLastMsgAndTime(String linkId, String content, Timestamp sendTime);

    @Query(value = "select window from chat_list where link_id =?1 and user_id =?2", nativeQuery = true)
    int selectIsSaveWindows(String linkId, String userId);

    @Query(value = "update chat_list set unread = unread + 1 where link_id =?1 and user_id =?2", nativeQuery = true)
    @Modifying
    void updateUnread(String linkId, String userId);

    @Query(value = "update chat_list set window = ?2 where user_id =?1", nativeQuery = true)
    @Modifying
    void updateWindows(String userId, int i);

    @Query(value = "update chat_list set status = ?3 where link_id = ?1 and user_id = ?2",nativeQuery = true)
    @Modifying
    void updateChatListStatus(String linkId, String userId, boolean b);

    @Query(value = "select new com.campuswindow.chat.vo.ChatListVo(c.listId, c.linkId, " +
            "case l.type " +
            "when 1 then (select g.groupName from ChatGroup as g join ChatLink as k on g.linkId = k.linkId where g.linkId = c.linkId) " +
            "when 0 then (select u.userName from ChatList as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end," +
            "case l.type " +
            "when 1 then (select g.groupAvatar from ChatGroup as g join ChatLink as k on g.linkId = k.linkId where g.linkId = c.linkId) " +
            "when 0 then (select u.avatar from ChatList as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end," +
            "case l.type " +
            "when 1 then (select g.groupNumber from ChatGroup as g join ChatLink as k on g.linkId = k.linkId where g.linkId = c.linkId) " +
            "when 0 then 2 end, " +
            "c.lastMsg, c.lastMsgTime, c.unread, c.status, l.type) " +
            "from ChatList as c join ChatLink as l on c.linkId = l.linkId " +
            "where c.userId = ?1 and (c.status = 1 or c.unread != 0) " +
            "order by c.lastMsgTime desc")
    List<ChatListVo> findAllByFromUserId(String userId);

    @Query(value = "select userId from ChatList where linkId = ?1")
    List<String> findUserIdByLinkId(String linkId);

//    @Query(value = "select new com.campuswindow.chat.vo.ChatListVo(c.userId, " +
//            "case l.type when 1 then l.name when 0 then (select u.userName from ChatList as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end," +
//            "case l.type when 1 then l.avatar when 0 then (select u.avatar from ChatList as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end ) " +
//            "from ChatList as c join ChatLink as l on c.linkId = l.linkId " +
//            "where c.userId = ?1")
    @Query(value = "select new com.campuswindow.chat.vo.ChatListFollowVo(c.linkId, " +
    "case l.type " +
            "when 1 then (select g.groupName from ChatGroup as g join ChatLink as k on g.linkId = k.linkId where g.linkId = c.linkId) " +
            "when 0 then (select u.userName from ChatList as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end," +
    "case l.type " +
            "when 1 then (select g.groupAvatar from ChatGroup as g join ChatLink as k on g.linkId = k.linkId where g.linkId = c.linkId) " +
            "when 0 then (select u.avatar from ChatList as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end ) " +
    "from ChatList as c join ChatLink as l on c.linkId = l.linkId " +
    "where c.userId = ?1 order by c.status desc")
    List<ChatListFollowVo> findFollowersByUserId(String userId);

    @Query(value = "select c1.linkId from ChatList as c1 join ChatList as c2 on c1.linkId = c2.linkId join ChatLink as k on k.linkId = c2.linkId where k.type = 0 and c1.userId = ?1 and c2.userId = ?2 ")
    String findLinkIdByUserIdAndToUserId(String userId, String toUserId);

    @Query(value = "delete from ChatList where linkId = ?1")
    @Modifying
    void deleteAllByLinkId(String linkId);

    @Query(value = "select new com.campuswindow.chat.vo.ChatListFollowVo(c.linkId, " +
            "case l.type " +
            "when 1 then (select g.groupName from ChatGroup as g join ChatLink as k on g.linkId = k.linkId where g.linkId = c.linkId) " +
            "when 0 then (select u.userName from ChatList as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end," +
            "case l.type " +
            "when 1 then (select g.groupAvatar from ChatGroup as g join ChatLink as k on g.linkId = k.linkId where g.linkId = c.linkId) " +
            "when 0 then (select u.avatar from ChatList as e join User as u on e.userId = u.userId where e.userId != ?1 and e.linkId = c.linkId) end) " +
            "from ChatList as c join ChatLink as l on c.linkId = l.linkId " +
            "where c.userId = ?1 ")
    List<ChatListFollowVo> findFollowerByName(String userId, String userName);

    @Query(value = "select count(*) from ChatList where linkId = ?1")
    int findChannelNumberByLinkId(String linkId);

    @Query(value = "delete from ChatList where linkId = ?1 and userId = ?2")
    @Modifying
    void deleteAllByLinkIdAndUserId(String channelLinkId, String userId);

    @Query(value = "select count(*) from ChatList where linkId = ?1 and userId = ?2")
    int findByLinkIdAndUserId(String linkId, String userId);

    @Query(value = "select unread from ChatList where linkId =?1")
    int findUnreadByLinkId(String linkId);
}
