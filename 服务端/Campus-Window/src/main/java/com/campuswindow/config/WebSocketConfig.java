package com.campuswindow.config;


import com.campuswindow.chat.service.ChatService;
import com.campuswindow.chat.websocket.WebSocketServer;
import com.campuswindow.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Autowired
    public void socketGroupService(ChatService chatService){
        WebSocketServer.chatService = chatService;
    }

    @Autowired
    public void socketUserService(UserService userService){
        WebSocketServer.userService= userService;
    }
    @Bean
    public ServerEndpointExporter exporter(){
        return new ServerEndpointExporter();
    }
}
