package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	@Query("select u.chat_id from Chat u where u.senderPhone=:senderId and u.receiverPhone=:reciverId")
	public int getChatId(@Param("senderId") String senderId,@Param("reciverId") String reciverId);
}
