package com.campuswindow.chat.controller;

import com.campuswindow.chat.dto.ChatChannelDto;
import com.campuswindow.chat.service.ChatService;
import com.campuswindow.chat.vo.*;
import com.campuswindow.fileupload.FileUploadService;
import com.campuswindow.utils.MinioConstant;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/chat")
@Tag(name = "聊天接口")
public class ChatController {
    private ChatService chatService;
    private FileUploadService fileUploadService;

    @Autowired
    public ChatController(ChatService chatService, FileUploadService fileUploadService) {
        this.chatService = chatService;
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/saveChatLinkGroup")
    @Operation(summary = "新建群组链接")
    public Result<?> saveChatLinkGroup(@RequestBody List<String> userIds) {
        chatService.saveForGroup(userIds);
        return ResultVOUtil.success();
    }
//    @PostMapping("/saveChatLinkP2P")
//    @Operation(summary = "新建私聊链接(用户点击关注，单方面建立链接)")
//    public Result<?> save(String fromUserId, String toUserId) {
//        chatService.saveForFollow(fromUserId, toUserId);
//        return ResultVOUtil.success();
//    }

    @GetMapping("/getChatList")
    @Operation(summary = "根据用户ID获取聊天列表")
    public Result<List<ChatListVo>> getChatList(String userId) {
        List<ChatListVo> chatLists = chatService.findAllByFromUserId(userId);
        return ResultVOUtil.success(chatLists);
    }

    @GetMapping("/getChatRecords")
    @Operation(summary = "获取聊天记录")
    public Result<List<ChatMessageVo>> recentChatRecords(String linkId){
        List<ChatMessageVo> chatMessageGroups = chatService.findAll(linkId);
        return  ResultVOUtil.success(chatMessageGroups);
    }

    @GetMapping("/updateEnterWindows")
    @Operation(summary = "进入聊天窗")
    public Result<String> updateEnterWindows(String linkId, String userId) {
        chatService.updateWindows(linkId, userId, 1);
        chatService.clearUnread(linkId, userId);
        String background = chatService.findChatBackground(linkId, userId);
        return ResultVOUtil.success(background);
    }

    @GetMapping("/updateLeaveWindows")
    @Operation(summary = "离开聊天窗")
    public Result<?> updateLeaveWindows(String linkId, String userId) {
        chatService.updateWindows(linkId, userId, 0);
        return ResultVOUtil.success();
    }

    @GetMapping("/findFollowers")
    @Operation(summary = "聊天列表查询想要聊天的人（基于朋友/关注/粉丝/群聊）")
    public Result<List<ChatListFollowVo>> findFollowers(String userId) {
        List<ChatListFollowVo> users = chatService.findFollowers(userId);
        return ResultVOUtil.success(users);
    }

    @GetMapping("/findFollowerByUserName")
    @Operation(summary = "聊天列表查询根据用户名或群名")
    public Result<List<ChatListFollowVo>> findFollowerByName(String userId, String name) {
        List<ChatListFollowVo> users = chatService.findFollowerByName(userId, name);
        return ResultVOUtil.success(users);
    }

    @GetMapping("/followOtherUser")
    @Operation(summary = "关注其他用户")
    public Result<?> followOtherUser(String userId, String toUserId){
        chatService.followOtherUser(userId, toUserId);
        return ResultVOUtil.success();
    }

    @GetMapping("/cancelFollowOtherUser")
    @Operation(summary = "取消关注其他用户")
    public Result<?> cancelFollowOtherUser(String userId, String toUserId){
        chatService.cancelFollowOtherUser(userId, toUserId);
        return ResultVOUtil.success();
    }

    @GetMapping("/modifyChatBackground")
    @Operation(summary = "修改聊天背景")
    public Result<String> modifyChatBackground(String linkId, String userId, MultipartFile background) {
        String fileUrl = fileUploadService.save(background, MinioConstant.CHAT_ROOT_PATH);
        chatService.modifyChatBackground(linkId, userId, fileUrl);
        return ResultVOUtil.success(fileUrl);
    }

    @GetMapping("/clearUnreadForMyself/{linkId}/{userId}")
    @Operation(summary = "自己跳转到对象窗口，清除未读消息")
    public Result<?> clearUnreadForMyself(@PathVariable("linkId") String linkId, @PathVariable("userId") String userId){
        chatService.clearUnreadForMyself(linkId, userId);
        chatService.updateChatListStatus(linkId, userId, true);
        return ResultVOUtil.success();
    }

    @GetMapping("/updateChatListStatus/{linkId}/{userId}")
    @Operation(summary = "用户删除与其他对象的聊天窗口，更改status")
    public Result<?> updateChatListStatus(@PathVariable("linkId") String linkId, @PathVariable("userId") String userId){
        chatService.updateChatListStatus(linkId, userId, false);
        return ResultVOUtil.success();
    }

    @GetMapping("/chatToOther")
    @Operation(summary = "私信按钮")
    public Result<String> chatToOther(String userId, String toUserId){
        String linkId = chatService.chatToOther(userId, toUserId);
        return ResultVOUtil.success(linkId);
    }

    @PostMapping("/createChannel")
    @Operation(summary = "创建频道")
    public Result<?> createChannel(@RequestBody ChatChannelDto chatChannelDto){
        chatService.createChannel(chatChannelDto);
        return ResultVOUtil.success();
    }

    @GetMapping("/deleteChannel")
    @Operation(summary = "删除频道")
    public Result<?> deleteChannel(String linkId, String userId){
        chatService.deleteChannel(linkId, userId);
        return ResultVOUtil.success();
    }

    @PostMapping("/createChannelChild")
    @Operation(summary = "创建子频道")
    public Result<?> createChannelChild(@RequestBody ChatChannelDto chatChannelDto){
        chatService.createChannelChild(chatChannelDto);
        return ResultVOUtil.success();
    }

    @GetMapping("/deleteChannelChild")
    @Operation(summary = "删除子频道")
    public Result<?> deleteChannelChild(String linkId){
        chatService.deleteChannelChild(linkId);
        return ResultVOUtil.success();
    }

    @GetMapping("/enterChannel")
    @Operation(summary = "加入频道")
    public Result<?> enterChannel(String linkId, String userId){
        chatService.enterChannel(linkId, userId);
        return ResultVOUtil.success();
    }

    @GetMapping("/leaveChannel")
    @Operation(summary = "退出频道")
    public Result<?> leaveChannel(String linkId, String userId){
        chatService.leaveChannel(linkId, userId);
        return ResultVOUtil.success();
    }

    @GetMapping("/findChannel")
    @Operation(summary = "查询用户已加入的频道")
    public Result<List<ChatChannelVo>> findChannel(String userId){
        List<ChatChannelVo> chatChannelVos = chatService.findChannel(userId);
        return ResultVOUtil.success(chatChannelVos);
    }

    @GetMapping("/findChannelDetail")
    @Operation(summary = "根据频道Id查询子频道")
    public Result<ChatChannelDetailVo> findChildChannel(String linkId, String userId){
        ChatChannelDetailVo chatChannelVos = chatService.findChildChannel(linkId, userId);
        return ResultVOUtil.success(chatChannelVos);
    }

    @PostMapping("/modifyChannel")
    @Operation(summary = "根据频道Id修改频道信息")
    public Result<List<?>> modifyChannel(@RequestBody ModifyChannelVo modifyChannelVo){
        chatService.modifyChannel(modifyChannelVo);
        return ResultVOUtil.success();
    }

    @GetMapping("/findPersonalInfo")
    @Operation(summary = "频道个人信息页面")
    public Result<ChannelPersonalInfoVo> findPersonalInfo(String userId){
        ChannelPersonalInfoVo channelPersonalInfoVo = chatService.findPersonalInfo(userId);
        return ResultVOUtil.success(channelPersonalInfoVo);
    }

    @GetMapping("/findAllChannel")
    @Operation(summary = "根据频道名称模糊查询频道")
    public Result<List<QueryChatChannelVo>> findAllChannel(String channelName, String userId){
        List<QueryChatChannelVo> queryChatChannelVo = chatService.findChannelByChannelName(channelName, userId);
        return ResultVOUtil.success(queryChatChannelVo);
    }

    @GetMapping("/ChannelHomePage")
    @Operation(summary = "频道主页（发现其他频道）")
    public Result<List<ChannelHomePageVo>> ChannelHomePage(String userId){
        List<ChannelHomePageVo> channelHomePageVos = chatService.findChannelHomePage(userId);
        return ResultVOUtil.success(channelHomePageVos);
    }

}
