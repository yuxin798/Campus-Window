package com.campuswindow.chat.p2p.repository;

import com.campuswindow.chat.p2p.entity.Msg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Msg, String> {
}
