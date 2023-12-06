package com.campuswindow.chat.controller;

import com.campuswindow.chat.dto.ChatListDto;
import com.campuswindow.chat.entity.ChatMessage;
import com.campuswindow.chat.entity.Msg;
import com.campuswindow.chat.service.ChatForService;
import com.campuswindow.chat.service.ChatService;
import com.campuswindow.utils.ResultVOUtil;
import com.campuswindow.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @PostMapping("/saveChatLinkForGroup")
//    @Operation(summary = "群组新建链接")
//    public Result<String> save(String[] userId) {
//        String groupId = UUID.randomUUID().toString().replaceAll("-", "");
//        for (String u : userId){
//            chatForService.saveForGroup(groupId, u);
//        }
//        return ResultVOUtil.success();
//    }

    @GetMapping("/getChatList")
    @Operation(summary = "根据发送者ID获取聊天列表")
    public Result<List<ChatListDto>> getChatList(String fromUserId) {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<ChatList> chatListPage = chatForService.findAllByFromUserId(pageable, fromUserId);
        List<ChatListDto> chatLists = chatForService.findAllByFromUserId(fromUserId);
        return ResultVOUtil.success(chatLists);
    }

//    @GetMapping("/getChatRecords")
//    @Operation(summary = "获取最近几次的信息")
//    public Result<Page<ChatMessageDto>> recentChatRecords(String fromUserId, String toUserId){
//        Sort.Order order = Sort.Order.desc("sendTime");
//        Sort sort = Sort.by(order);
//        Page<ChatMessageDto> chatMessageDtoPage = chatForService.findAllByFromUserIdAndToUserId(PageRequest.of(0, 6, sort), fromUserId, toUserId);
//        System.out.println(chatMessageDtoPage.getContent());
//        //        Collections.reverse(chatMessageDtoPage.getContent());
//        return  ResultVOUtil.success(chatMessageDtoPage);
//    }

    @GetMapping("/getChatRecords")
    @Operation(summary = "获取聊天记录")
    public Result<List<ChatMessage>> recentChatRecords(String fromUserId, String toUserId){
        List<ChatMessage> chatMessages = chatForService.findAll(fromUserId, toUserId);
        //        Collections.reverse(chatMessageDtoPage.getContent());
        return  ResultVOUtil.success(chatMessages);
    }

    @GetMapping("/updateEnterWindows")
    @Operation(summary = "进入聊天窗")
    public Result updateEnterWindows(String fromUserId, String toUserId) {
        chatForService.updateWindows(fromUserId, toUserId, 1);
        chatForService.clearUnread(fromUserId, toUserId);
        return ResultVOUtil.success();
    }

    @GetMapping("/updateLeaveWindows")
    @Operation(summary = "离开聊天窗")
    public Result updateLeaveWindows(String fromUserId, String toUserId) {
        chatForService.updateWindows(fromUserId, toUserId, 0);
        return ResultVOUtil.success();
    }

    @GetMapping("/updateChatListStatus/{fromUserId}/{toUserId}")
    @Operation(summary = "用户删除与其他对象的聊天窗口，更改status")
    public Result updateChatListStatus(@PathVariable("fromUserId") String fromUserId, @PathVariable("toUserId") String toUserId){
        chatForService.updateChatListStatus(fromUserId, toUserId, false);
        return ResultVOUtil.success();
    }

    @GetMapping("/clearUnreadForMyself/{fromUserId}/{toUserId}")
    @Operation(summary = "自己跳转到对象窗口，清除未读消息")
    public Result clearUnreadForMyself(@PathVariable("fromUserId") String fromUserId, @PathVariable("toUserId") String toUserId){
        chatForService.clearUnreadForMyself(fromUserId, toUserId);
        chatForService.updateChatListStatus(fromUserId, toUserId, true);
        return ResultVOUtil.success();
    }
}
