package com.campuswindow.chat.service;

import com.campuswindow.chat.dto.ChatMessageDto;
import com.campuswindow.chat.entity.ChatLink;
import com.campuswindow.chat.entity.ChatLinkGroup;
import com.campuswindow.chat.entity.ChatList;
import com.campuswindow.chat.entity.ChatMessage;
import com.campuswindow.chat.repository.ChatLinkGroupRepository;
import com.campuswindow.chat.repository.ChatLinkRepository;
import com.campuswindow.chat.repository.ChatListRepository;
import com.campuswindow.chat.repository.ChatMessageRepository;
import com.campuswindow.user.entity.User;
import com.campuswindow.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
    @Autowired
    private ChatLinkGroupRepository chatLinkGroupRepository;

    public String findLinkIdByFromUserIdAndToUserId(String fromUserId, String toUserId){
        return chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
    }
    public void saveForPrivate(String fromUserId, String toUserId) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        ChatLink chatLink = new ChatLink();
        chatLink.setLinkId(linkId);
        chatLink.setFromUserId(fromUserId);
        chatLink.setToUserId(toUserId);
        chatLink.setCreateTime(new Timestamp(System.currentTimeMillis()));
        chatLinkRepository.save(chatLink);

        User fromUser = userRepository.findUserNameAndAvatarByUserId(fromUserId);
        User toUser = userRepository.findUserNameAndAvatarByUserId(toUserId);
//            String lastMsg = chatMessageRepository.findLastContentByFromUserIdAndToUserId(fromUserId, toUserId);
//            if (lastMsg == null){
//                lastMsg = "";
//            }
        String listId = UUID.randomUUID().toString().replaceAll("-", "");
        ChatList fromUserList = new ChatList(listId, linkId, fromUserId, toUserId, toUser.getUserName(), toUser.getAvatar(), null,  0, false);
        listId = UUID.randomUUID().toString().replaceAll("-", "");
        ChatList toUserList = new ChatList(listId, linkId, toUserId, fromUserId, fromUser.getUserName(), fromUser.getAvatar(), null, 0, false);
        chatListRepository.save(fromUserList);
        chatListRepository.save(toUserList);
    }

    public void saveForGroup(String groupId, String userId) {
        String publicId = UUID.randomUUID().toString().replaceAll("-", "");
        ChatLinkGroup chatLinkGroup = new ChatLinkGroup();
        chatLinkGroup.setPublicId(publicId);
        chatLinkGroup.setGroupId(groupId);
        chatLinkGroup.setUserId(userId);
        chatLinkGroup.setCreateTime(new Timestamp(System.currentTimeMillis()));
        chatLinkGroupRepository.save(chatLinkGroup);
    }

    public Page<ChatList> findAllByFromUserId(Pageable pageable, String fromUserId) {
        return chatListRepository.findAllByFromUserId(pageable, fromUserId);
    }

    public void saveMessage(boolean flag, ChatMessage chatMessage) {
        String linkId = chatMessage.getLinkId();
        String fromUserId = chatMessage.getFromUserId();
        String toUserId = chatMessage.getToUserId();

        //将一条的信息的状态（最后一条）改为否
        chatMessageRepository.updateMessageStatus(linkId);

//        //判断聊天双方是否在同一窗口聊天
//        int flag = chatListRepository.selectIsSaveWindows(linkId, fromUserId, toUserId);
//
//        // 1--只有一方在窗口中 未读数加给接收方，2--两者都在窗口中 清除未读数
//        if (flag == 1) {
//            //更新未读数,
//            chatListRepository.updateUnread(linkId, fromUserId, toUserId, 1);
//        } else if (flag == 2) {
//            //清空所有的未读数
//            chatListRepository.clearUnread(linkId, fromUserId, toUserId);
//        }

        if (flag == false){
            chatListRepository.updateUnread(linkId, fromUserId, toUserId, 1);
        }
        chatMessage.setMessageId(UUID.randomUUID().toString().replaceAll("-", ""));
        chatMessage.setSendTime(new Timestamp(System.currentTimeMillis()));
        //保存聊天记录
        chatMessageRepository.save(chatMessage);
    }

    public void clearUnread(String fromUserId, String toUserId){
        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
        chatListRepository.clearUnread(linkId, fromUserId, toUserId);
        chatListRepository.clearUnread(linkId, toUserId, fromUserId);
    }

//    public void updateWindows(String fromUserId, String toUserId, boolean flag) {
//        //获取两者之间的关联id
//        String linkId = chatLinkRepository.findLinkIdByFromUserIdAndToUserId(fromUserId, toUserId);
//        //更新点击了聊天框的同一窗口值
//        chatListRepository.updateFromWindows(linkId, fromUserId, flag);
//        chatListRepository.updateToWindows(linkId, toUserId, flag);
//    }


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

    public Page<ChatMessageDto> findAllByFromUserIdAndToUserId(PageRequest of, String fromUserId, String toUserId) {
        return chatMessageRepository.findAllByFromUserIdAndToUserId(of, fromUserId, toUserId).map(chatMessage ->
            new ChatMessageDto(chatMessage.getToUserId(), chatMessage.getContent(), chatMessage.getSendTime())
        );
    }
}
