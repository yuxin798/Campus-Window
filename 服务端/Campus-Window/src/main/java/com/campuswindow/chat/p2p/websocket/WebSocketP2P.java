//package com.campuswindow.chat.p2p.websocket;
//
//import com.alibaba.fastjson.JSON;
//import com.campuswindow.chat.p2p.entity.ChatMessage;
//import com.campuswindow.chat.p2p.service.ChatForService;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//@ServerEndpoint("/websocket/{fromUserId}")
//public class WebSocketP2P {
//    private static Map<String, Session> clients = new ConcurrentHashMap<>();
//    private String fromUserId;
//    private static Integer number = 0;//在线用户数
//    public static ChatForService chatForService;
//    boolean flag = false;
//
//    @OnOpen
//    public void OnOpen(Session session, @PathParam("fromUserId") String fromUserId){
//        this.fromUserId = fromUserId;
//        number++;
////        sendMessageToAll(JSON.toJSONString(number));
//        clients.put(fromUserId, session);
////        chatForService.updateWindows(fromUserId, toUserId, 1);
//    }
//
//    @OnClose
//    public void onClose(Session session) {
//        number--;
////        sendMessageToAll(JSON.toJSONString(number));
////        chatForService.updateWindows(fromUserId, toUserId, 0);
//
//        CloseReason close = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "关闭客户端，下线！");
//        try {
//            session.close(close);
//            clients.remove(fromUserId);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        // 判断当前连接是否还在线
//    }
//
//    @OnMessage
//    public void onMessage(String message) {
////        ChatMessageDto chatMessageDto = JSON.parseObject(message, ChatMessageDto.class);
//        ChatMessage chatMessage = JSON.parseObject(message, ChatMessage.class);
////        String toUserId = chatMessage.getToUserId();
//        if (toUserId == null){
//            sendMessageToAll(message);
//        }else {
//            sendMessageToOne(message);
//        }
//        chatForService.saveMessage(chatMessage);
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        CloseReason close = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "关闭客户端，下线！");
//        try {
//            session.close(close);
//            clients.remove(fromUserId);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void sendMessageToAll(String message){
//        for (Map.Entry<String, Session> entry : clients.entrySet()) {
//            if (!entry.getKey().equals(fromUserId)){
//                entry.getValue().getAsyncRemote().sendText(message);
//            }
//        }
//    }
//
//    public void sendMessageToOne(String message){
//        for (Map.Entry<String, Session> entry : clients.entrySet()) {
//            if (entry.getKey().equals(toUserId)){
//                entry.getValue().getAsyncRemote().sendText(message);
//                break;
//            }
//        }
//    }
//}
