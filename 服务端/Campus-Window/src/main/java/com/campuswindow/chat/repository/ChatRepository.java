package com.campuswindow.chat.repository;

import com.campuswindow.chat.entity.Msg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Msg, String> {
}
