package com.campuswindow.config;

import com.campuswindow.chat.service.ChatForService;
import com.campuswindow.chat.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Autowired
    public void socketUserService(ChatForService chatForService){
        WebSocketServer.chatForService= chatForService;
    }
    @Bean
    public ServerEndpointExporter exporter(){
        return new ServerEndpointExporter();
    }
}
