package com.campuswindow.chat.controller;

import com.campuswindow.chat.dto.ChatMessageDto;
import com.campuswindow.chat.entity.ChatList;
import com.campuswindow.chat.entity.Msg;
import com.campuswindow.chat.service.ChatForService;
import com.campuswindow.chat.service.ChatService;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "聊天接口")
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatForService chatForService;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有信息接口")
    public Result<List<Msg>> findAll(){
        List<Msg> msgs = chatService.findAll();
        return ResultVOUtil.success(msgs);
    }

    @GetMapping("/save")
    @Operation(summary = "保存信息接口")
    public Result<String> save(Msg msg){
        chatService.save(msg);
        return ResultVOUtil.success();
    }

    @PostMapping("/saveChatLink")
    @Operation(summary = "发送者和接收者新建链接")
    public Result<String> save(String fromUserId, String toUserId) {
        chatForService.saveForPrivate(fromUserId, toUserId);
        return ResultVOUtil.success();
    }

    @PostMapping("/saveChatLinkForGroup")
    @Operation(summary = "群组新建链接")
    public Result<String> save(String[] userId) {
        String groupId = UUID.randomUUID().toString().replaceAll("-", "");
        for (String u : userId){
            chatForService.saveForGroup(groupId, u);
        }
        return ResultVOUtil.success();
    }

    @GetMapping("/getChatList")
    @Operation(summary = "根据发送者ID获取聊天列表")
    public Result<Page<ChatList>> getChatList(String fromUserId) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ChatList> chatListPage = chatForService.findAllByFromUserId(pageable, fromUserId);
        return ResultVOUtil.success(chatListPage);
    }

    @GetMapping("/getChatRecords")
    @Operation(summary = "获取最近几次的信息")
    public Result<Page<ChatMessageDto>> recentChatRecords(String fromUserId, String toUserId){
        Sort.Order order = Sort.Order.desc("sendTime");
        Sort sort = Sort.by(order);
        Page<ChatMessageDto> chatMessageDtoPage = chatForService.findAllByFromUserIdAndToUserId(PageRequest.of(0, 6, sort), fromUserId, toUserId);
        System.out.println(chatMessageDtoPage.getContent());
        //        Collections.reverse(chatMessageDtoPage.getContent());
        return  ResultVOUtil.success(chatMessageDtoPage);
    }

//    @GetMapping("/updateEnterWindows")
//    @Operation(summary = "进入聊天窗")
//    public Result updateEnterWindows(String fromUserId, String toUserId) {
//        chatForService.updateWindows(fromUserId, toUserId, true);
//        return ResultVOUtil.success();
//    }
//
//    @GetMapping("/updateLeaveWindows")
//    @Operation(summary = "离开聊天窗")
//    public Result updateLeaveWindows(String fromUserId, String toUserId) {
//        chatForService.updateWindows(fromUserId, toUserId, false);
//        return ResultVOUtil.success();
//    }
}
