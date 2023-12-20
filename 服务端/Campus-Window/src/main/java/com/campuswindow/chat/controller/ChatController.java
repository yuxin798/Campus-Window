package com.campuswindow.chat.controller;

import com.campuswindow.chat.service.ChatService;
import com.campuswindow.chat.vo.ChatListVo;
import com.campuswindow.chat.vo.ChatMessageVo;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@Tag(name = "聊天接口")
public class ChatController {
    private ChatService chatService;

    @PostMapping("/saveChatLinkGroup")
    @Operation(summary = "新建群组链接")
    public Result<?> save(@RequestBody List<String> userIds) {
        chatService.saveForGroup(userIds);
        return ResultVOUtil.success();
    }

    @PostMapping("/saveChatLinkP2P")
    @Operation(summary = "新建私聊链接")
    public Result<?> save(String fromUserId, String toUserId) {
        chatService.saveForPrivate(fromUserId, toUserId);
        return ResultVOUtil.success();
    }

    @GetMapping("/getChatList")
    @Operation(summary = "根据发送者ID获取聊天列表")
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
    public Result<?> updateEnterWindows(String linkId, String userId) {
        chatService.updateWindows(linkId, userId, 1);
        chatService.clearUnread(linkId, userId);
        return ResultVOUtil.success();
    }

    @GetMapping("/updateLeaveWindows")
    @Operation(summary = "离开聊天窗")
    public Result<?> updateLeaveWindows(String linkId, String userId) {
        chatService.updateWindows(linkId, userId, 0);
        return ResultVOUtil.success();
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

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }
}
