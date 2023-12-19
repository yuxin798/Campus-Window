package com.campuswindow.chat.service;

import com.campuswindow.chat.entity.ChatLink;
import com.campuswindow.chat.entity.ChatList;
import com.campuswindow.chat.entity.ChatMessage;
import com.campuswindow.chat.repository.ChatLinkRepository;
import com.campuswindow.chat.repository.ChatListRepository;
import com.campuswindow.chat.repository.ChatMessageRepository;
import com.campuswindow.chat.vo.ChatListVo;
import com.campuswindow.chat.vo.ChatMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChatService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatLinkRepository chatLinkRepository;
    @Autowired
    private ChatListRepository chatListRepository;

    public void saveForGroup(List<String> userIds) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        chatLinkRepository.save(new ChatLink(linkId, linkId, createTime, 1, "hello", null, userIds.size()));
        for (String userId : userIds){
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            chatListRepository.save(new ChatList(id, linkId, userId, 0, null, createTime, 0, 1));
        }
    }

    public void saveForPrivate(String fromUserId, String toUserId) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        chatLinkRepository.save(new ChatLink(linkId, linkId, createTime, 0, null, null, 2));
        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, fromUserId, 0, "请开始聊天吧", createTime, 0, 1));
        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, toUserId, 0, "请开始聊天吧", createTime, 0, 1));
    }

    public List<ChatMessageVo> findAll(String linkId) {
        return chatMessageRepository.findAll(linkId);
    }

    public void updateWindows(String linkId, String userId, int i) {
        chatListRepository.updateWindows(linkId, userId, i);
    }

    public void clearUnread(String linkId, String userId) {
        chatListRepository.clearUnread(linkId, userId);
    }

    public List<String> findUserIdByLinkId(String linkId) {
        return chatListRepository.findUserIdByLinkId(linkId);
    }

    public void saveMessage(ChatMessageVo chatMessageGroup, List<String> userIds) {
        String messageId = UUID.randomUUID().toString().replaceAll("-", "");
        chatMessageGroup.setMessageId(messageId);
        String linkId = chatMessageGroup.getLinkId();
        //更改最后一条的信息内容和时间
        chatListRepository.updateLastMsgAndTime(linkId, chatMessageGroup.getContent(), new Timestamp(System.currentTimeMillis()));

        //判断聊天是否在窗口聊天
        for (String userId : userIds) {
            // 1--只有一方在窗口中 未读数加给接收方，2--两者都在窗口中 清除未读数
            int flag = chatListRepository.selectIsSaveWindows(linkId, userId);
            if (flag == 0){
                //更新未读数,
                chatListRepository.updateUnread(linkId, userId);
            }else {
                //清空所有的未读数
                chatListRepository.clearUnread(linkId, userId);
            }
        }
        //保存聊天记录
        chatMessageRepository.save(new ChatMessage(messageId, linkId, chatMessageGroup.getUserId(), chatMessageGroup.getContent(), new Timestamp(System.currentTimeMillis()), chatMessageGroup.getType()));
    }

    public void updateWindows(String userId, int i) {
        chatListRepository.updateWindows(userId, 0);
    }

    public void clearUnreadForMyself(String linkId, String userId) {
        chatListRepository.clearUnread(linkId, userId);
    }

    public void updateChatListStatus(String linkId, String userId, boolean b) {
        chatListRepository.updateChatListStatus(linkId, userId, b);
    }

    public List<ChatListVo> findAllByFromUserId(String userId) {
        return chatListRepository.findAllByFromUserId(userId);
    }
}
