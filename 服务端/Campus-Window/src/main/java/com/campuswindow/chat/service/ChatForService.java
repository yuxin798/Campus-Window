package com.campuswindow.chat.service;

import com.campuswindow.chat.dto.ChatListDto;
import com.campuswindow.chat.entity.ChatLink;
import com.campuswindow.chat.entity.ChatList;
import com.campuswindow.chat.entity.ChatMessage;
import com.campuswindow.chat.repository.ChatLinkRepository;
import com.campuswindow.chat.repository.ChatListRepository;
import com.campuswindow.chat.repository.ChatMessageRepository;
import com.campuswindow.user.entity.User;
import com.campuswindow.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChatForService {
    @Autowired
    private ChatListRepository chatListRepository;
    @Autowired
    private ChatLinkRepository chatLinkRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private UserRepository userRepository;

    public String findLinkIdByFromUserIdAndToUserId(String fromUserId, String toUserId){
        return chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
    }
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

        User fromUser = userRepository.findUserNameAndAvatarByUserId(fromUserId);
        User toUser = userRepository.findUserNameAndAvatarByUserId(toUserId);
//            String lastMsg = chatMessageRepository.findLastContentByFromUserIdAndToUserId(fromUserId, toUserId);
//            if (lastMsg == null){
//                lastMsg = "";
//            }
        String listId = UUID.randomUUID().toString().replaceAll("-", "");
        ChatList fromUserList = new ChatList(listId, linkId, fromUserId, toUserId, 0, 0, null,  0, 1);
        listId = UUID.randomUUID().toString().replaceAll("-", "");
        ChatList toUserList = new ChatList(listId, linkId, toUserId, fromUserId, 0, 0, null, 0, 1);
        chatListRepository.save(fromUserList);
        chatListRepository.save(toUserList);
    }


    public Page<ChatList> findAllByFromUserId(Pageable pageable, String fromUserId) {
        return chatListRepository.findAllByFromUserId(pageable, fromUserId);
    }

    public void saveMessage(ChatMessage chatMessage) {
        String fromUserId = chatMessage.getFromUserId();
        String toUserId = chatMessage.getToUserId();
        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        chatMessage.setLinkId(linkId);
        System.out.println(chatMessage);

        //将一条的信息的状态（最后一条）改为否
//        chatMessageRepository.updateMessageStatus(linkId);
        chatListRepository.updateLastMsg(linkId, chatMessage.getContent());

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
//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setLinkId(linkId);
//        chatMessage.setFromUserId(fromUserId);
//        chatMessage.setToUserId(toUserId);
//        chatMessage.setContent(chatMessageDto.getContent());
//        chatMessage.setType(0);
//        chatMessage.setMessageId(UUID.randomUUID().toString().replaceAll("-", ""));
//        chatMessage.setSendTime(new Timestamp(System.currentTimeMillis()));
        //保存聊天记录
        chatMessageRepository.save(chatMessage);
    }

    public void clearUnreadForMyself(String fromUserId, String toUserId){
        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        chatListRepository.clearUnread(linkId, fromUserId, toUserId);
    }


    public void clearUnread(String fromUserId, String toUserId){
        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        chatListRepository.clearUnread(linkId, fromUserId, toUserId);
        chatListRepository.clearUnread(linkId, toUserId, fromUserId);
    }

    public void updateWindows(String fromUserId, String toUserId, int flag) {
        //获取两者之间的关联id
        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        //更新点击了聊天框的同一窗口值
        chatListRepository.updateFromWindows(linkId, fromUserId, flag);
        chatListRepository.updateToWindows(linkId, fromUserId, flag);
    }


//    public List<ChatList> findChatListByUserId(String fromUserId) {
//        return chatListRepository.findChatListByFromUserId(fromUserId);
//    }

//    public List<ChatMessageDto> getRecentChatRecords(String fromUserId, String toUserId) {
//
//        //获取两者之间的关联id
//        ChatLink chatLink = chatLinkRepository.findChatLinkByFromUserIdAndToUserId(fromUserId, toUserId);
//        //查询最近的六条聊天信息（包括未读）
//        List<ChatMessaged> chatMessageDtos = chatMessageRepository.findRecentMessage(chatLink.getLinkId());
//        //反转list
//        Collections.reverse(chatMessageDtos);
//        //清空未读信息
////        chatMapper.clearUnread(fromUser, toUser, linkId);
//
//        return chatMessageDtos;
//    }

//    public Page<ChatMessageDto> findAllByFromUserIdAndToUserId(PageRequest of, String fromUserId, String toUserId) {
//        return chatMessageRepository.findAllByFromUserIdAndToUserId(of, fromUserId, toUserId).map(chatMessage ->
//            new ChatMessageDto(chatMessage.getToUserId(), chatMessage.getContent(), chatMessage.getSendTime())
//        );
//    }

    public List<ChatListDto> findAllByFromUserId(String fromUserId) {
        return chatListRepository.findAllByFromUserId(fromUserId);
    }


    public List<ChatMessage> findAll(String fromUserId, String toUserId) {
//        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        return chatMessageRepository.findAll(fromUserId, toUserId);
    }

    public void updateChatListStatus(String fromUserId, String toUserId, boolean flag) {
        chatListRepository.updateChatListStatus(fromUserId, toUserId, flag);
    }
}
