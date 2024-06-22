package com.example.ututor.ChatSystem.Chat;

import com.example.ututor.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Optional<Chat> findById(long id);

    List<Chat> findByUsersContaining(UserEntity User);
}
