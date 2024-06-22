package com.example.ututor.ChatSystem.Message;

import com.example.ututor.ChatSystem.Chat.Chat;
import com.example.ututor.User.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
