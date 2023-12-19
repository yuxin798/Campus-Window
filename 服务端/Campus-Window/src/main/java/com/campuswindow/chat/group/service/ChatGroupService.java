package com.campuswindow.chat.group.service;

import com.campuswindow.chat.group.entity.ChatLinkGroup;
import com.campuswindow.chat.group.entity.ChatListGroup;
import com.campuswindow.chat.group.entity.ChatMessageGroup;
import com.campuswindow.chat.group.repository.ChatLinkGroupRepository;
import com.campuswindow.chat.group.repository.ChatListGroupRepository;
import com.campuswindow.chat.group.repository.ChatMessageGroupRepository;
import com.campuswindow.chat.group.vo.ChatListVo;
import com.campuswindow.chat.group.vo.ChatMessageGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChatGroupService {
    @Autowired
    private ChatMessageGroupRepository chatMessageGroupRepository;
    @Autowired
    private ChatLinkGroupRepository chatLinkGroupRepository;
    @Autowired
    private ChatListGroupRepository chatListGroupRepository;

    public void saveForGroup(List<String> userIds) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        chatLinkGroupRepository.save(new ChatLinkGroup(linkId, linkId, createTime, 1, "hello", null, userIds.size()));
        for (String userId : userIds){
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            chatListGroupRepository.save(new ChatListGroup(id, linkId, userId, 0, null, createTime, 0, 1));
        }
    }

    public void saveForPrivate(String fromUserId, String toUserId) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        chatLinkGroupRepository.save(new ChatLinkGroup(linkId, linkId, createTime, 0, null, null, 2));
        chatListGroupRepository.save(new ChatListGroup(UUID.randomUUID().toString().replaceAll("-", ""), linkId, fromUserId, 0, "请开始聊天吧", createTime, 0, 1));
        chatListGroupRepository.save(new ChatListGroup(UUID.randomUUID().toString().replaceAll("-", ""), linkId, toUserId, 0, "请开始聊天吧", createTime, 0, 1));
    }

    public List<ChatMessageGroupVo> findAll(String linkId) {
        return chatMessageGroupRepository.findAll(linkId);
    }

    public void updateWindows(String linkId, String userId, int i) {
        chatListGroupRepository.updateWindows(linkId, userId, i);
    }

    public void clearUnread(String linkId, String userId) {
        chatListGroupRepository.clearUnread(linkId, userId);
    }

    public List<String> findUserIdByLinkId(String linkId) {
        return chatListGroupRepository.findUserIdByLinkId(linkId);
    }

    public void saveMessage(ChatMessageGroupVo chatMessageGroup, List<String> userIds) {
        String messageId = UUID.randomUUID().toString().replaceAll("-", "");
        chatMessageGroup.setMessageId(messageId);
        String linkId = chatMessageGroup.getLinkId();
        //更改最后一条的信息内容和时间
        chatListGroupRepository.updateLastMsgAndTime(linkId, chatMessageGroup.getContent(), new Timestamp(System.currentTimeMillis()));

        //判断聊天是否在窗口聊天
        for (String userId : userIds) {
            // 1--只有一方在窗口中 未读数加给接收方，2--两者都在窗口中 清除未读数
            int flag = chatListGroupRepository.selectIsSaveWindows(linkId, userId);
            if (flag == 0){
                //更新未读数,
                chatListGroupRepository.updateUnread(linkId, userId);
            }else {
                //清空所有的未读数
                chatListGroupRepository.clearUnread(linkId, userId);
            }
        }
        //保存聊天记录
        chatMessageGroupRepository.save(new ChatMessageGroup(messageId, linkId, chatMessageGroup.getUserId(), chatMessageGroup.getContent(), new Timestamp(System.currentTimeMillis()), chatMessageGroup.getType()));
    }

    public void updateWindows(String userId, int i) {
        chatListGroupRepository.updateWindows(userId, 0);
    }

    public void clearUnreadForMyself(String linkId, String userId) {
        chatListGroupRepository.clearUnread(linkId, userId);
    }

    public void updateChatListStatus(String linkId, String userId, boolean b) {
        chatListGroupRepository.updateChatListStatus(linkId, userId, b);
    }

    public List<ChatListVo> findAllByFromUserId(String userId) {
        return chatListGroupRepository.findAllByFromUserId(userId);
    }
}
