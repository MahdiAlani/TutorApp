package com.example.ututor.ChatSystem.Message;

import com.example.ututor.ChatSystem.Chat.Chat;
import com.example.ututor.User.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final com.example.ututor.ChatSystem.Message.MessageRepository MessageRepository;

    public MessageService(com.example.ututor.ChatSystem.Message.MessageRepository MessageRepository) {
        this.MessageRepository = MessageRepository;
    }

    public List<Message> getMessagesFromChat(Chat chat) {
        return MessageRepository.findByChat(chat);
    }

    public Message getMessageById(int id) {
        return MessageRepository.findById(id).orElse(null);
    }

    public Message saveMessage(Message Message) {
        return MessageRepository.save(Message);
    }

    public void deleteMessage(int id) {
        MessageRepository.deleteById(id);
    }
}