package com.campuswindow.chat.repository;

import com.campuswindow.chat.entity.ChatLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatLinkRepository extends JpaRepository<ChatLink, String> {
}
