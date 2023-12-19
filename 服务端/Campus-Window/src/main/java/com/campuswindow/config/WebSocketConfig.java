package com.campuswindow.config;


import com.campuswindow.chat.group.service.ChatGroupService;
import com.campuswindow.chat.group.websocket.WebSocketGroup;
import com.campuswindow.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Autowired
    public void socketGroupService(ChatGroupService chatGroupService){
        WebSocketGroup.chatGroupService= chatGroupService;
    }

    @Autowired
    public void socketUserService(UserService userService){
        WebSocketGroup.userService= userService;
    }
    @Bean
    public ServerEndpointExporter exporter(){
        return new ServerEndpointExporter();
    }
}
