package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.model.ChatMessage;

public interface PrivateChatReadRepository extends JpaRepository<ChatMessage, Integer> {

	@Query("select count(u) as maxid from ChatMessage u where u.chat_id=:chatid ")
	public int privatechatMaxid(@Param("chatid") int chatid);
	
	@Query("select u from ChatMessage u where u.chat_id=:chatid")
	public List<ChatMessage> readMessage(@Param("chatid")int chatid);
}
