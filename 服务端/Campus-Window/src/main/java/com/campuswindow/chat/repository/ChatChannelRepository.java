package com.campuswindow.chat.repository;

import com.campuswindow.chat.entity.ChatChannel;
import com.campuswindow.chat.vo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatChannelRepository extends JpaRepository<ChatChannel, String> {
    @Query(value = "select distinct new com.campuswindow.chat.vo.ChatChannelVo(c1.linkId, c1.channelAvatar) from ChatChannel as c1 join ChatChannel as c2 on c1.linkId = c2.parentId join ChatList as l on c1.linkId = l.linkId where l.userId = ?1")
    List<ChatChannelVo> findChannelByUserId(String userId);

    @Query(value = "select new com.campuswindow.chat.vo.ChatChannelDetailVo(linkId, channelName, channelAvatar, channelMaster) from ChatChannel where linkId = ?1")
    ChatChannelDetailVo findChildChannelByLinkId(String linkId);

    @Query(value = "select new com.campuswindow.chat.vo.ChatChannelListVo(l.listId, c1.linkId, c1.channelName, c1.channelAvatar, l.lastMsg) from ChatChannel as c1 join ChatChannel as c2 on c1.parentId = c2.linkId join ChatList as l on c1.linkId = l.linkId where c1.parentId = ?1 and l.userId = ?2")
    List<ChatChannelListVo> findChannelListByParentId(String linkId, String userId);

    @Query(value = "select new com.campuswindow.chat.vo.EnterChannelVo(c1.linkId, l.lastMsg, l.lastMsgTime, (select count(*) from ChatMessage where linkId = ?1)) from ChatChannel as c1 join ChatChannel as c2 on c1.parentId = c2.linkId join ChatList as l on c1.linkId = l.linkId where c1.parentId = ?1")
    List<EnterChannelVo> findChildChannelByParentId(String linkId);

    @Query(value = "select c1.linkId from ChatChannel as c1 join ChatChannel as c2 on c1.parentId = c2.linkId where c1.parentId = ?1")
    @Modifying
    List<String> findChildChannelLinkIdByParentId(String linkId);

    @Query(value = "delete from ChatChannel where linkId = ?1")
    @Modifying
    void deleteChannel(String linkId);

    @Query(value = "select new com.campuswindow.chat.vo.QueryChatChannelVo(c.linkId, c.channelName, c.channelAvatar, c.channelSignature) from ChatChannel as c where channelName like %?1%")
    List<QueryChatChannelVo> findAllByChannelNameContainingIgnoreCase(String channelName);
}
