package com.campuswindow.chat.websocket;

import com.alibaba.fastjson.JSON;
import com.campuswindow.chat.entity.User;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServer {
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    private String userId;
    private static Integer number = 0;

    @OnOpen
    public void OnOpen(Session session, @PathParam("userId") String userId){
        this.userId = userId;
        number++;
        sendMessageToAll(JSON.toJSONString(number));
        clients.put(userId, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        number--;
        sendMessageToAll(JSON.toJSONString(number));
        clients.remove(userId);
    }

    @OnMessage
    public void onMessage(String message) {
        User user = JSON.parseObject(message, User.class);
        if (user.getMsg().getType() == 1){
            System.out.println("朕来");
            sendMessageToOne(user.getMsg().getToUserId(), message);
        }else {
            sendMessageToAll(message);
        }
    }

    public void sendMessageToAll(String message){
        for (Map.Entry<String, Session> entry : clients.entrySet()) {
            if (!entry.getKey().equals(userId)){
                entry.getValue().getAsyncRemote().sendText(message);
            }
        }
    }

    public void sendMessageToOne(String toUserId, String message){
        for (Map.Entry<String, Session> entry : clients.entrySet()) {
            if (entry.getKey().equals(toUserId)){
                entry.getValue().getAsyncRemote().sendText(message);
            }
        }
    }
}
