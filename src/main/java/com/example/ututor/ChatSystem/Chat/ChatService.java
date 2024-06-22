package com.example.ututor.ChatSystem.Chat;

import com.example.ututor.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Chat getChatById(int id) {
        return chatRepository.findById(id).orElse(null);
    }

    public List<Chat> getChatsByUser(UserEntity user) {
        return chatRepository.findByUsersContaining(user);
    }

    public Chat saveChat(Chat Chat) {
        return chatRepository.save(Chat);
    }

    public void deleteChat(int id) {
        chatRepository.deleteById(id);
    }
}