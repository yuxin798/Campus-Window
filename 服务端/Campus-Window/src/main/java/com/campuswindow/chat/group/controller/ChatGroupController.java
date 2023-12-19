package com.campuswindow.chat.group.controller;

import com.campuswindow.chat.group.service.ChatGroupService;
import com.campuswindow.chat.group.vo.ChatListVo;
import com.campuswindow.chat.group.vo.ChatMessageGroupVo;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatGroup")
public class ChatGroupController {
    @Autowired
    private ChatGroupService chatGroupService;

    @PostMapping("/saveChatLinkGroup")
    @Operation(summary = "新建群组链接")
    public Result<?> save(@RequestBody List<String> userIds) {
        chatGroupService.saveForGroup(userIds);
        return ResultVOUtil.success();
    }

    @PostMapping("/saveChatLinkP2P")
    @Operation(summary = "新建私聊链接")
    public Result<?> save(String fromUserId, String toUserId) {
        chatGroupService.saveForPrivate(fromUserId, toUserId);
        return ResultVOUtil.success();
    }

    @GetMapping("/getChatList")
    @Operation(summary = "根据发送者ID获取聊天列表")
    public Result<List<ChatListVo>> getChatList(String userId) {
        List<ChatListVo> chatLists = chatGroupService.findAllByFromUserId(userId);
        return ResultVOUtil.success(chatLists);
    }

    @GetMapping("/getChatRecords")
    @Operation(summary = "获取聊天记录")
    public Result<List<ChatMessageGroupVo>> recentChatRecords(String linkId){
        List<ChatMessageGroupVo> chatMessageGroups = chatGroupService.findAll(linkId);
        return  ResultVOUtil.success(chatMessageGroups);
    }

    @GetMapping("/updateEnterWindows")
    @Operation(summary = "进入聊天窗")
    public Result<?> updateEnterWindows(String linkId, String userId) {
        chatGroupService.updateWindows(linkId, userId, 1);
        chatGroupService.clearUnread(linkId, userId);
        return ResultVOUtil.success();
    }

    @GetMapping("/updateLeaveWindows")
    @Operation(summary = "离开聊天窗")
    public Result<?> updateLeaveWindows(String linkId, String userId) {
        chatGroupService.updateWindows(linkId, userId, 0);
        return ResultVOUtil.success();
    }

    @GetMapping("/clearUnreadForMyself/{userId}/{linkId}")
    @Operation(summary = "自己跳转到对象窗口，清除未读消息")
    public Result<?> clearUnreadForMyself(@PathVariable("userId") String userId, @PathVariable("linkId") String linkId){
        chatGroupService.clearUnreadForMyself(linkId, userId);
        chatGroupService.updateChatListStatus(linkId, userId, true);
        return ResultVOUtil.success();
    }

    @GetMapping("/updateChatListStatus/{fromUserId}/{toUserId}")
    @Operation(summary = "用户删除与其他对象的聊天窗口，更改status")
    public Result<?> updateChatListStatus(@PathVariable("fromUserId") String fromUserId, @PathVariable("toUserId") String toUserId){
        chatGroupService.updateChatListStatus(fromUserId, toUserId, false);
        return ResultVOUtil.success();
    }
}
