//package com.campuswindow.config;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeSet;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//@Component
//@ServerEndpoint("/websocket/{userName}")
//public class WebSocket {
//    private Session session;
//    private String userName;
//    private static Integer userNumber = 0;
//    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();
//
//    //前端请求时一个websocket时
//    @OnOpen
//    public void onOpen(Session session, @PathParam("userName") String userName) throws IOException {
//        this.session = session;
//        //将当前对象放入webSocketSet
//        webSocketSet.add(this);
//        //增加在线人数
//        userNumber++;
//        //保存当前用户名
//        this.userName = userName;
//        //获得所有的用户
//        Set<String> userLists = new TreeSet<>();
//        for (WebSocket webSocket : webSocketSet) {
//            userLists.add(webSocket.userName);
//        }
//
//        //将所有信息包装好传到客户端(给所有用户)
//        Map<String, Object> map1 = new HashMap();
//        //  把所有用户列表
//        map1.put("onlineUsers", userLists);
//        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
//        map1.put("messageType", 1);
//        //  返回用户名
//        map1.put("username", userName);
//        //  返回在线人数
//        map1.put("number", this.userNumber);
//        //发送给所有用户谁上线了，并让他们更新自己的用户菜单
//        sendMessageAll(JSON.toJSONString(map1),this.userName);
//
//        // 更新在线人数(给所有人)
//        Map<String, Object> map2 = new HashMap();
//        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
//        map2.put("messageType", 3);
//        //把所有用户放入map2
//        map2.put("onlineUsers", userLists);
//        //返回在线人数
//        map2.put("number", this.userNumber);
//        // 消息发送指定人（所有的在线用户信息）
//        sendMessageAll(JSON.toJSONString(map2),this.userName);
//    }
//
//    //前端关闭时一个websocket时
//    @OnClose
//    public void onClose() throws IOException {
//        //从集合中移除当前对象
//        webSocketSet.remove(this);
//        //在线用户数减少
//        userNumber--;
//        Map<String, Object> map1 = new HashMap();
//        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
//        map1.put("messageType", 2);
//        //所有在线用户
//        map1.put("onlineUsers", this.webSocketSet);
//        //下线用户的用户名
//        map1.put("username", this.userName);
//        //返回在线人数
//        map1.put("number", userNumber);
//        //发送信息，所有人，通知谁下线了
//        sendMessageAll(JSON.toJSONString(map1),this.userName);
//    }
//
//    //前端向后端发送消息
//    @OnMessage
//    public void onMessage(String message) throws IOException {
//        //将前端传来的数据进行转型
//        JSONObject jsonObject = JSON.parseObject(message);
//        //获取所有数据
//        String textMessage = jsonObject.getString("message");
//        String username = jsonObject.getString("username");
//        String type = jsonObject.getString("type");
//        String toUserName = jsonObject.getString("toUserName");
//        //群发
//        if("群发".equals(type)){
//            Map<String, Object> map3 = new HashMap();
//            map3.put("messageType", 4);
//            //所有在线用户
//            map3.put("onlineUsers", this.webSocketSet);
//            //发送消息的用户名
//            map3.put("username", username);
//            //返回在线人数
//            map3.put("number", userNumber);
//            //发送的消息
//            map3.put("textMessage", textMessage);
//            //发送信息，所有人，通知谁下线了
//            sendMessageAll(JSON.toJSONString(map3),this.userName);
//        }
//        //私发
//        else{
//            //发送给对应的私聊用户
//            Map<String, Object> map3 = new HashMap();
//            map3.put("messageType", 4);
//            //所有在线用户
//            map3.put("onlineUsers", this.webSocketSet);
//            //发送消息的用户名
//            map3.put("username", username);
//            //返回在线人数
//            map3.put("number", userNumber);
//            //发送的消息
//            map3.put("textMessage", textMessage);
//            //发送信息，所有人，通知谁下线了
//            sendMessageTo(JSON.toJSONString(map3),toUserName);
//
//            //发送给自己
////            Map<String, Object> map4 = new HashMap();
////            map4.put("messageType", 4);
////            //所有在线用户
////            map4.put("onlineUsers", this.webSocketSet);
////            //发送消息的用户名
////            map4.put("username", username);
////            //返回在线人数
////            map4.put("number", userNumber);
////            //发送的消息
////            map4.put("textMessage", textMessage);
////            //发送信息，所有人，通知谁下线了
////            sendMessageTo(JSON.toJSONString(map3),username);
//        }
//    }
//
//    /**
//     *  消息发送所有人
//     */
//    public void sendMessageAll(String message, String FromUserName) throws IOException {
//        for (WebSocket webSocket: webSocketSet) {
//            //消息发送所有人（同步）getAsyncRemote
//            webSocket.session.getBasicRemote().sendText(message);
//        }
//    }
//
//    /**
//     *  消息发送指定人
//     */
//    public void sendMessageTo(String message, String ToUserName) throws IOException {
//        //遍历所有用户
//        for (WebSocket webSocket : webSocketSet) {
//            if (webSocket.userName.equals(ToUserName)) {
//                //消息发送指定人
//                webSocket.session.getBasicRemote().sendText(message);
//                break;
//            }
//        }
//    }
//}
