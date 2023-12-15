package com.campuswindow.chat.service;

import com.campuswindow.chat.dto.ChatListDto;
import com.campuswindow.chat.entity.ChatLink;
import com.campuswindow.chat.entity.ChatList;
import com.campuswindow.chat.entity.ChatMessage;
import com.campuswindow.chat.repository.ChatLinkRepository;
import com.campuswindow.chat.repository.ChatListRepository;
import com.campuswindow.chat.repository.ChatMessageRepository;
import com.campuswindow.user.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChatForService {

    private ChatListRepository chatListRepository;
    private ChatLinkRepository chatLinkRepository;
    private ChatMessageRepository chatMessageRepository;
    private UserRepository userRepository;

    public String findLinkIdByFromUserIdAndToUserId(String fromUserId, String toUserId){
        return chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
    }

    /*
     * 两个用户新建链接
     */
    public void saveForPrivate(String fromUserId, String toUserId) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        ChatLink chatLink = new ChatLink();
        chatLink.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        chatLink.setLinkId(linkId);
        chatLink.setFromUserId(fromUserId);
        chatLink.setToUserId(toUserId);
        chatLink.setCreateTime(new Timestamp(System.currentTimeMillis()));
        chatLinkRepository.save(chatLink);
        chatLink.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        chatLink.setFromUserId(toUserId);
        chatLink.setToUserId(fromUserId);
        chatLinkRepository.save(chatLink);

        String listId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp lastMsgTime = new Timestamp(System.currentTimeMillis());
        ChatList fromUserList = new ChatList(listId, linkId, fromUserId, toUserId, 0, 0, null, lastMsgTime, 0, 1);
        listId = UUID.randomUUID().toString().replaceAll("-", "");
        ChatList toUserList = new ChatList(listId, linkId, toUserId, fromUserId, 0, 0, null, lastMsgTime, 0, 1);
        chatListRepository.save(fromUserList);
        chatListRepository.save(toUserList);
    }

    /*
     * 保存消息到数据库
     */
    public void saveMessage(ChatMessage chatMessage) {
        String fromUserId = chatMessage.getFromUserId();
        String toUserId = chatMessage.getToUserId();
        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        chatMessage.setLinkId(linkId);

        //更改最后一条的信息内容和时间
        chatListRepository.updateLastMsgAndTime(linkId, chatMessage.getContent(), chatMessage.getSendTime());


        //判断聊天双方是否在同一窗口聊天
        int flag = chatListRepository.selectIsSaveWindows(linkId, fromUserId, toUserId);

        // 1--只有一方在窗口中 未读数加给接收方，2--两者都在窗口中 清除未读数
        if (flag == 1) {
            //更新未读数,
            chatListRepository.updateUnread(linkId, fromUserId, toUserId);
        } else if (flag == 2) {
            //清空所有的未读数
            chatListRepository.clearUnread(linkId, fromUserId, toUserId);
        }
        //保存聊天记录
        chatMessageRepository.save(chatMessage);
    }

    /*
     * 自己进入窗口后清空未读信息
     */
    public void clearUnreadForMyself(String fromUserId, String toUserId){
        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        chatListRepository.clearUnread(linkId, fromUserId, toUserId);
    }

    /*
     * 双方都在窗口，同时清除未读信息
     */
    public void clearUnread(String fromUserId, String toUserId){
        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        chatListRepository.clearUnread(linkId, fromUserId, toUserId);
        chatListRepository.clearUnread(linkId, toUserId, fromUserId);
    }

    /*
     * 更新窗口状态
     */
    public void updateWindows(String fromUserId, String toUserId, int flag) {
        //获取两者之间的关联id
        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        //更新点击了聊天框的同一窗口值
        chatListRepository.updateFromWindows(linkId, fromUserId, flag);
        chatListRepository.updateToWindows(linkId, fromUserId, flag);
    }

    /*
     * 获取聊天列表
     */
    public List<ChatListDto> findAllByFromUserId(String fromUserId) {
        return chatListRepository.findAllByFromUserId(fromUserId);
    }

    /*
     * 获取两个用户之间的聊天记录
     */
    public List<ChatMessage> findAll(String fromUserId, String toUserId) {
        return chatMessageRepository.findAll(fromUserId, toUserId);
    }

    /*
     * 更新聊天列表状态
     */
    public void updateChatListStatus(String fromUserId, String toUserId, boolean flag) {
        chatListRepository.updateChatListStatus(fromUserId, toUserId, flag);
    }

    @Autowired
    public ChatForService(ChatListRepository chatListRepository, ChatLinkRepository chatLinkRepository, ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.chatListRepository = chatListRepository;
        this.chatLinkRepository = chatLinkRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }
}
