package com.example.ututor.ChatSystem.Message;

import com.example.ututor.ChatSystem.Chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Optional<Message> findById(long id);

    List<Message> findByChat(Chat chat);
}
