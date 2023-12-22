package com.campuswindow.chat.repository;

import com.campuswindow.chat.entity.ChatLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChatLinkRepository extends JpaRepository<ChatLink, String> {
    @Query(value = "delete from ChatLink where linkId = ?1")
    @Modifying
    void deleteByLinkId(String linkId);
}
