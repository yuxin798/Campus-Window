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

    @Query(value = "select new com.campuswindow.chat.vo.ChatChannelDetailVo(linkId, channelName, channelAvatar,channelBackground, channelMaster) from ChatChannel where linkId = ?1")
    ChatChannelDetailVo findChildChannelByLinkId(String linkId);

    @Query(value = "select distinct new com.campuswindow.chat.vo.ChatChannelListVo(c1.linkId, c1.channelName, c1.channelAvatar, l.lastMsg) from ChatChannel as c1 join ChatChannel as c2 on c1.parentId = c2.linkId join ChatList as l on c1.linkId = l.linkId where c1.parentId = ?1 and l.userId = ?2")
    List<ChatChannelListVo> findChannelListByParentId(String linkId, String userId);

    @Query(value = "select distinct new com.campuswindow.chat.vo.ChatChannelListVo(c1.linkId, c1.channelName, c1.channelAvatar, l.lastMsg) from ChatChannel as c1 join ChatChannel as c2 on c1.parentId = c2.linkId join ChatList as l on c1.linkId = l.linkId where c1.parentId = ?1")
    List<ChatChannelListVo> findChannelListByParentId(String linkId);

    @Query(value = "select new com.campuswindow.chat.vo.EnterChannelVo(c1.linkId, l.lastMsg, l.lastMsgTime, (select count(*) from ChatMessage where linkId = ?1)) from ChatChannel as c1 join ChatChannel as c2 on c1.parentId = c2.linkId join ChatList as l on c1.linkId = l.linkId where c1.parentId = ?1")
    List<EnterChannelVo> findChildChannelByParentId(String linkId);

    @Query(value = "select c1.linkId from ChatChannel as c1 join ChatChannel as c2 on c1.parentId = c2.linkId where c1.parentId = ?1")
    @Modifying
    List<String> findChildChannelLinkIdByParentId(String linkId);

    @Query(value = "delete from ChatChannel where linkId = ?1")
    @Modifying
    void deleteChannel(String linkId);

    @Query(value = "select new com.campuswindow.chat.vo.QueryChatChannelVo(c.linkId, c.channelName, c.channelAvatar, c.channelSignature) from ChatChannel as c join ChatLink as l on c.linkId = l.linkId where l.type = 2")
    List<QueryChatChannelVo> findAllChannel();

    @Query(value = "select new com.campuswindow.chat.vo.QueryChatChannelVo(c.linkId, c.channelName, c.channelAvatar, c.channelSignature) from ChatChannel as c join ChatLink as l on c.linkId = l.linkId where c.channelName like %?1% and l.type = 2")
    List<QueryChatChannelVo> findAllByChannelNameContainingIgnoreCase(String channelName);

    @Query(value = "update ChatChannel set channelName = ?2, channelAvatar = ?3, channelSignature = ?4, channelBackground = ?5 where linkId = ?1")
    @Modifying
    void updateChannelByLinkId(String linkId, String channelName, String channelAvatar, String channelSignature, String channelBackground);

    @Query(value = "select new com.campuswindow.chat.vo.ChannelPersonalInfoVo(u.userName, u.avatar, u.school, u.gender) from User as u where u.userId = ?1")
    ChannelPersonalInfoVo findPersonalInfo(String userId);

    @Query(value = "select new com.campuswindow.chat.vo.ChannelInfoVo(c.linkId, c.channelName, c.channelAvatar, c.channelSignature) from ChatChannel as c join ChatLink as l on c.linkId = l.linkId where c.channelMaster = ?1 and l.type = 2")
    List<ChannelInfoVo> findMyselfChannels(String userId);

    @Query(value = "select new com.campuswindow.chat.vo.ChannelInfoVo(c.linkId, c.channelName, c.channelAvatar, c.channelSignature) from ChatChannel as c join ChatList as l on c.linkId = l.linkId join ChatLink as k on k.linkId = l.linkId where l.userId = ?1 and k.type = 2 and c.channelMaster != ?1")
    List<ChannelInfoVo> findOtherChannels(String userId);

}
