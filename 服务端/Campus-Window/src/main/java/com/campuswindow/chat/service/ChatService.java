package com.campuswindow.chat.service;

import com.campuswindow.chat.entity.Msg;
import com.campuswindow.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private ChatRepository chatRepository;

    public List<Msg> findAll() {
        return chatRepository.findAll();
    }

    public void save(Msg msg) {
        chatRepository.save(msg);
    }

    @Autowired
    public void setChatRepository(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
}
