package com.campuswindow.chat.service;

import com.campuswindow.chat.dto.ChatChannelDto;
import com.campuswindow.chat.entity.*;
import com.campuswindow.chat.repository.*;
import com.campuswindow.chat.vo.*;
import com.campuswindow.user.follow.service.FollowService;
import com.campuswindow.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatLinkRepository chatLinkRepository;
    private final ChatListRepository chatListRepository;
    private final ChatGroupRepository chatGroupRepository;
    private final ChatChannelRepository chatChannelRepository;
    private final UserService userService;
    private final FollowService followService;

    @Autowired
    public ChatService(ChatMessageRepository chatMessageRepository, ChatLinkRepository chatLinkRepository, ChatListRepository chatListRepository, ChatGroupRepository chatGroupRepository, ChatChannelRepository chatChannelRepository, UserService userService, FollowService followService) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatLinkRepository = chatLinkRepository;
        this.chatListRepository = chatListRepository;
        this.chatGroupRepository = chatGroupRepository;
        this.chatChannelRepository = chatChannelRepository;
        this.userService = userService;
        this.followService = followService;
    }

    public void saveForGroup(List<String> userIds) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        StringBuilder builder = new StringBuilder();
        for (String s : userIds) {
            String username = userService.findUserNameByUserId(s);
            builder.append("，").append(username);
        }
        // TODO 默认图片自行设置
        chatGroupRepository.save(new ChatGroup(linkId, builder.toString(), "http://8.130.17.7:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg", userIds.size()));
        chatLinkRepository.save(new ChatLink(linkId, linkId, createTime, 1));
        for (String userId : userIds){
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            chatListRepository.save(new ChatList(id, linkId, userId, 0, null, createTime, 0, 1, null));
        }
    }

    public void saveForPrivate(String fromUserId, String toUserId) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        chatLinkRepository.save(new ChatLink(linkId, linkId, createTime, 0));
        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, fromUserId, 0, "请开始聊天吧", createTime, 0, 0, null));
        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, toUserId, 0, "请开始聊天吧", createTime, 0, 0, null));
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
        chatListRepository.updateWindows(userId, i);
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

    /*
     * 查找聊天id
     */
    public String findLinkIdByUserIdAndToUserId(String userId, String toUserId) {
        return chatListRepository.findLinkIdByUserIdAndToUserId(userId, toUserId);
    }

    /**
     * 删除聊天
     */
    public void deleteByLinkId(String linkId) {
        chatListRepository.deleteAllByLinkId(linkId);
        chatLinkRepository.deleteByLinkId(linkId);
    }

    /**
     * 关注
     */
    public void followOtherUser(String userId, String toUserId) {
        followService.followOtherUser(userId, toUserId);
        userService.updateFollowersByUserId(userId, 1);
        userService.updateFansByUserId(toUserId, 1);
        int count = followService.findCountByUserIdAndToUserId(userId, toUserId);
        if (count == 2){
            userService.updateFriendsByUserIdAndToUserId(userId, toUserId, 1);
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

    /**
     * 取消关注
     */
    public void cancelFollowOtherUser(String userId, String toUserId) {
        int count = followService.findCountByUserIdAndToUserId(userId, toUserId);
        if (count == 2){
            userService.updateFriendsByUserIdAndToUserId(userId, toUserId, -1);
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

    /**
     * 查找关注者
     */
    public List<ChatListFollowVo> findFollowerByName(String userId, String name) {
        if (name == null ){
            name = "";
        }
        String finalName = name;
        return chatListRepository.findFollowerByName(userId)
               .stream()
               .filter(e -> e.getName().contains(finalName))
               .collect(Collectors.toList());
    }

    /**
     * 私信
     */
    public String chatToOther(String userId, String toUserId) {
        String linkId = chatListRepository.findLinkIdByUserIdAndToUserId(userId, toUserId);
        if (linkId == null){
            linkId = UUID.randomUUID().toString().replaceAll("-", "");
            Timestamp createTime = new Timestamp(System.currentTimeMillis());
            chatLinkRepository.save(new ChatLink(linkId, linkId, createTime, 0));
            chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, userId, 0, "请开始聊天吧", createTime, 0, 0, null));
            chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, toUserId, 0, "请开始聊天吧", createTime, 0, 0, null));
        }
        return linkId;
    }

    /**
     * 创建频道
     */
    public void createChannel(ChatChannelDto chatChannelDto) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        chatLinkRepository.save(new ChatLink(UUID.randomUUID().toString().replaceAll("-", ""), linkId, createTime, 2));
        chatChannelRepository.save(new ChatChannel(linkId, chatChannelDto.getChannelName(), chatChannelDto.getChannelAvatar(), chatChannelDto.getChannelSignature(), chatChannelDto.getParentId(), chatChannelDto.getChannelMaster(), chatChannelDto.getChannelBackground()));
        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, chatChannelDto.getChannelMaster(), 0,  null, createTime, 0, 1, null));
    }

    /**
     * 创建子频道
     */
    public void createChannelChild(ChatChannelDto chatChannelDto) {
        String linkId = UUID.randomUUID().toString().replaceAll("-", "");
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        chatLinkRepository.save(new ChatLink(UUID.randomUUID().toString().replaceAll("-", ""), linkId, createTime, 3));
        chatChannelRepository.save(new ChatChannel(linkId, chatChannelDto.getChannelName(), chatChannelDto.getChannelAvatar(), null, chatChannelDto.getParentId(), chatChannelDto.getChannelMaster(), chatChannelDto.getChannelBackground()));
        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, chatChannelDto.getChannelMaster(), 0, "欢迎来到 " + chatChannelDto.getChannelName(), createTime, 0, 1, null));
    }

    /**
     * 加入频道
     */
    public void enterChannel(String linkId, String userId) {
        chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), linkId, userId, 0, null, new Timestamp(System.currentTimeMillis()), 0, 1, null));
        List<EnterChannelVo> enterChannelVos = chatChannelRepository.findChildChannelByParentId(linkId);
        for (EnterChannelVo enterChannelVo : enterChannelVos){
            chatListRepository.save(new ChatList(UUID.randomUUID().toString().replaceAll("-", ""), enterChannelVo.getLinkId(), userId, 0, enterChannelVo.getLastMsg(), new Timestamp(enterChannelVo.getLastMsgTime().getTime()), (int)enterChannelVo.getUnread(), 1, null));
        }
    }

    /**
     * 退出频道
     */
    public void leaveChannel(String linkId, String userId) {
        chatChannelRepository.findChildChannelLinkIdByParentId(linkId)
                .forEach(channelLinkId -> chatListRepository.deleteAllByLinkIdAndUserId(channelLinkId, userId));
        chatListRepository.deleteAllByLinkIdAndUserId(linkId, userId);
    }

    /**
     * 查询用户已加入的频道
     */
    public List<ChatChannelVo> findChannel(String userId) {
        return chatChannelRepository.findChannelByUserId(userId);
    }

    /**
     * 查找子频道
     */
    public ChatChannelDetailVo findChildChannel(String linkId, String userId) {
        ChatChannelDetailVo chatChannelDetailVo = chatChannelRepository.findChildChannelByLinkId(linkId);
        boolean entered = chatListRepository.findByLinkIdAndUserId(linkId, userId) == 1;
        List<ChatChannelListVo> chatChannelListVos;
        if (entered){
            chatChannelListVos = chatChannelRepository.findChannelListByParentId(linkId, userId)
                    .stream()
                    .peek(e -> e.setUnread(chatListRepository.findUnreadByLinkId(e.getLinkId())))
                    .collect(Collectors.toList());
        }else {
            chatChannelListVos = chatChannelRepository.findChannelListByParentId(linkId)
                   .stream()
                   .peek(e -> e.setUnread(chatMessageRepository.findUnreadByLinkId(e.getLinkId())))
                   .collect(Collectors.toList());
        }
        chatChannelDetailVo.setChatChannelListVos(chatChannelListVos);
        int count = chatListRepository.findChannelNumberByLinkId(linkId);
        chatChannelDetailVo.setChannelNumber(count);
        return chatChannelDetailVo;
    }

    /**
     * 删除子频道
     */
    public void deleteChannelChild(String linkId) {
        chatChannelRepository.deleteChannel(linkId);
        chatLinkRepository.deleteByLinkId(linkId);
        chatListRepository.deleteAllByLinkId(linkId);
        chatMessageRepository.deleteAllByLinkId(linkId);
    }

    /**
     * 删除频道
     */
    public void deleteChannel(String linkId, String userId) {
        List<String> allLinkIds = chatChannelRepository.findChildChannelLinkIdByParentId(linkId);
        allLinkIds.add(linkId);
        for (String channelLinkId : allLinkIds){
            chatChannelRepository.deleteChannel(channelLinkId);
            chatLinkRepository.deleteByLinkId(channelLinkId);
            chatListRepository.deleteAllByLinkIdAndUserId(channelLinkId, userId);
            chatMessageRepository.deleteAllByLinkId(channelLinkId);
        }
    }

    /**
     * 根据频道名称模糊查询频道
     */
    public List<QueryChatChannelVo> findChannelByChannelName(String channelName, String userId) {
        if (channelName == null || channelName.isEmpty()){
            return chatChannelRepository.findAllChannel()
                    .stream()
                    .peek(e -> e.setEntered(existsByLinkIdAndUserId(e.getLinkId(), userId)))
                    .peek(e -> e.setChannelNumber(chatListRepository.findChannelNumberByLinkId(e.getLinkId())))
                    .collect(Collectors.toList());
        }
        return chatChannelRepository.findAllByChannelNameContainingIgnoreCase(channelName)
                .stream()
                .peek(e -> e.setEntered(existsByLinkIdAndUserId(e.getLinkId(), userId)))
                .peek(e -> e.setChannelNumber(chatListRepository.findChannelNumberByLinkId(e.getLinkId())))
                .collect(Collectors.toList());
    }

    /**
     * 判断用户是否在频道中
     */
    private boolean existsByLinkIdAndUserId(String linkId, String userId) {
        return chatListRepository.findByLinkIdAndUserId(linkId, userId) != 0;
    }

    /**
     * 修改频道信息
     */
    public void modifyChannel(ModifyChannelVo modifyChannelVo) {
        chatChannelRepository.updateChannelByLinkId(modifyChannelVo.getLinkId(), modifyChannelVo.getChannelName(), modifyChannelVo.getChannelAvatar(), modifyChannelVo.getChannelSignature(), modifyChannelVo.getChannelBackground());
    }

    /**
     * 频道查询个人信息
     */
    public ChannelPersonalInfoVo findPersonalInfo(String userId) {
        ChannelPersonalInfoVo personalInfo = chatChannelRepository.findPersonalInfo(userId);
        List<ChannelInfoVo> myselfChannelInfoVos = chatChannelRepository.findMyselfChannels(userId)
                .stream()
                .peek(e -> e.setChannelNumber(chatListRepository.findChannelNumberByLinkId(e.getLinkId())))
                .collect(Collectors.toList());
        personalInfo.setMyselfChannels(myselfChannelInfoVos);
        List<ChannelInfoVo> OtherChannelInfoVos = chatChannelRepository.findOtherChannels(userId)
                .stream()
                .peek(e -> e.setChannelNumber(chatListRepository.findChannelNumberByLinkId(e.getLinkId())))
                .collect(Collectors.toList());
        personalInfo.setOtherChannels(OtherChannelInfoVos);
        return personalInfo;
    }

    /**
     * 查询频道主页
     */
    public List<ChannelHomePageVo> findChannelHomePage(String userId) {
        List<String> linkIds = chatListRepository.findLinkIdByUserId(userId);
        List<ChannelHomePageVo> homePageChannels = chatChannelRepository.findHomePageChannels(userId);
        homePageChannels.removeIf(channelHomePageVo -> linkIds.contains(channelHomePageVo.getLinkId()));
        return homePageChannels
                .stream()
                .peek(e -> e.setChannelNumber(chatListRepository.findChannelNumberByLinkId(e.getLinkId())))
                .collect(Collectors.toList());
    }

    /**
     * 查询聊天背景
     */
    public String findChatBackground(String linkId, String userId) {
        return chatListRepository.findChatBackground(linkId, userId);
    }

    /**
     * 修改聊天背景
     */
    public void modifyChatBackground(String linkId, String userId, String background) {
        chatListRepository.updateChatBackground(linkId, userId, background);
    }
}
