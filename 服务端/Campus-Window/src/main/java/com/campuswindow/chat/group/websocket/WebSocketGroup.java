package com.campuswindow.chat.group.websocket;

import com.alibaba.fastjson.JSON;
import com.campuswindow.chat.group.service.ChatGroupService;
import com.campuswindow.chat.group.vo.ChatMessageGroupVo;
import com.campuswindow.user.user.dto.ChatUserDto;
import com.campuswindow.user.user.service.UserService;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketGroup {
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    private String userId;
    private static Integer number = 0;//在线用户数
    public static ChatGroupService chatGroupService;
    public static UserService userService;

    @OnOpen
    public void OnOpen(Session session, @PathParam("userId") String userId){
        this.userId = userId;
        number++;
        clients.put(userId, session);
    }

    @OnClose
    public void onClose(Session session) {
        number--;
//        sendMessageToAll(JSON.toJSONString(number));
        chatGroupService.updateWindows(userId, 0);

        CloseReason close = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "关闭客户端，下线！");
        try {
            session.close(close);
            clients.remove(userId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 判断当前连接是否还在线
    }

    @OnMessage
    public void onMessage(String message) {
        ChatMessageGroupVo chatMessageGroup = JSON.parseObject(message, ChatMessageGroupVo.class);
        String linkId = chatMessageGroup.getLinkId();
        ChatUserDto chatUserDto = userService.findChatUserByUserId(chatMessageGroup.getUserId());
        chatMessageGroup.setUserName(chatUserDto.getUserName());
        chatMessageGroup.setAvatar(chatUserDto.getAvatar());
        List<String> userIds = chatGroupService.findUserIdByLinkId(linkId);
        userIds.remove(userId);
        sendMessage(message, userIds);
        System.out.println(chatMessageGroup.toString());
        chatGroupService.saveMessage(chatMessageGroup, userIds);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        chatGroupService.updateWindows(userId, 0);
        CloseReason close = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "关闭客户端，下线！");
        try {
            session.close(close);
            clients.remove(userId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message, List<String> userIds){
        for (Map.Entry<String, Session> entry : clients.entrySet()) {
            for (String userId : userIds){
                System.out.println(userId);
                if (entry.getKey().equals(userId)){
                    entry.getValue().getAsyncRemote().sendText(message);
                    break;
                }
            }
        }
    }
}
