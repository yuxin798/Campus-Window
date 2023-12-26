package com.campuswindow.chat.repository;

import com.campuswindow.chat.entity.ChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatGroupRepository extends JpaRepository<ChatGroup, String> {
}
