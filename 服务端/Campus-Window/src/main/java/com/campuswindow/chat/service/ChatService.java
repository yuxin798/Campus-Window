package com.campuswindow.chat.service;

import com.campuswindow.chat.entity.ChatLink;
import com.campuswindow.chat.entity.ChatList;
import com.campuswindow.chat.entity.ChatMessage;
import com.campuswindow.chat.repository.ChatLinkRepository;
import com.campuswindow.chat.repository.ChatListRepository;
import com.campuswindow.chat.repository.ChatMessageRepository;
import com.campuswindow.chat.vo.ChatListFollowVo;
import com.campuswindow.chat.vo.ChatListVo;
import com.campuswindow.chat.vo.ChatMessageVo;
import com.campuswindow.user.follow.service.FollowService;
import com.campuswindow.user.user.repository.UserRepository;
import com.campuswindow.user.user.service.UserService;
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
    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;
    @Autowired
    private UserRepository userRepository;

    public void saveForGroup(List<String> userIds) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < userIds.size(); i++) {
            String username = userService.findUserNameByUserId(userIds.get(i));
            builder.append(username);
        }
        // TODO 默认图片自行设置
        chatLinkRepository.save(new ChatLink(linkId, linkId, createTime, 1, builder.toString(), "http://192.168.144.132:9000/campus-bucket/users/default.jpg", userIds.size()));
        for (String userId : userIds){
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            chatListRepository.save(new ChatList(id, linkId, userId, 0, null, createTime, 0, 1));
        }
    }

    public void saveForPrivate(String fromUserId, String toUserId) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        chatLinkRepository.save(new ChatLink(linkId, linkId, createTime, 0, null, null, 2));
        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, fromUserId, 0, "请开始聊天吧", createTime, 0, 0));
        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, toUserId, 0, "请开始聊天吧", createTime, 0, 0));
    }

//    public void saveForFollow(String fromUserId, String toUserId) {
////        boolean followed = followService.findFollowByUserIdAndToUserId(toUserId, fromUserId);
////        if (followed == false){
//        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
//        Timestamp createTime = new Timestamp(System.currentTimeMillis());
//        chatLinkRepository.save(new ChatLink(linkId, linkId, createTime, 0, null, null, 2));
//        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, fromUserId, 0, "请开始聊天吧", createTime, 0, 0));
//        userService.followOtherUser(fromUserId, toUserId);
//    }

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

    public List<ChatListFollowVo> findFollowers(String userId) {
        return chatListRepository.findFollowersByUserId(userId);
    }

    public String findLinkIdByUserIdAndToUserId(String userId, String toUserId) {
        return chatListRepository.findLinkIdByUserIdAndToUserId(userId, toUserId);
    }

    public void deleteByLinkId(String linkId) {
        chatListRepository.deleteAllByLinkId(linkId);
        chatLinkRepository.deleteByLinkId(linkId);
    }

    public void followOtherUser(String userId, String toUserId) {
        followService.followOtherUser(userId, toUserId);
        userService.updateFollowersByUserId(userId, 1);
        userService.updateFansByUserId(toUserId, 1);
        int count = followService.findCountByUserIdAndToUserId(userId, toUserId);
        if (count == 2){
            updateFriendsByUserIdAndToUserId(userId, toUserId, 1);
            String linkId = findLinkIdByUserIdAndToUserId(userId, toUserId);
            updateChatListStatus(linkId, userId, true);
            updateChatListStatus(linkId, toUserId, true);
        }else {
            String linkId = findLinkIdByUserIdAndToUserId(userId, toUserId);
            if (linkId == null){
                saveForPrivate(userId, toUserId);
            }
        }
    }

    public void cancelFollowOtherUser(String userId, String toUserId) {
        int count = followService.findCountByUserIdAndToUserId(userId, toUserId);
        if (count == 2){
            updateFriendsByUserIdAndToUserId(userId, toUserId, -1);
            String linkId = findLinkIdByUserIdAndToUserId(userId, toUserId);
            updateChatListStatus(linkId, userId, false);
        }else {
            String linkId = findLinkIdByUserIdAndToUserId(userId, toUserId);
            deleteByLinkId(linkId);
        }
        followService.cancelFollowOtherUser(userId, toUserId);
        userService.updateFollowersByUserId(userId, -1);
        userService.updateFansByUserId(toUserId, -1);
    }

    public void updateFriendsByUserIdAndToUserId(String userId, String toUserId, int i) {
        userRepository.updateFriendsByUserIdAndToUserId(userId, i);
        userRepository.updateFriendsByUserIdAndToUserId(toUserId, i);

    }

    public List<ChatListFollowVo> findFollowerByName(String userId, String userName) {
       return chatListRepository.findFollowerByName(userId, userName);
    }

    public String chatToOther(String userId, String toUserId) {
        String linkId = chatListRepository.findLinkIdByUserIdAndToUserId(userId, toUserId);
        if (linkId == null){
            linkId = UUID.randomUUID().toString().replaceAll("-", "");
            Timestamp createTime = new Timestamp(System.currentTimeMillis());
            chatLinkRepository.save(new ChatLink(linkId, linkId, createTime, 0, null, null, 2));
            chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, userId, 0, "请开始聊天吧", createTime, 0, 0));
            chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, toUserId, 0, "请开始聊天吧", createTime, 0, 0));
        }
        return linkId;
    }
}
