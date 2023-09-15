package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sample.model.ChatHistory;

public interface PrivateChatRepository extends JpaRepository<ChatHistory, Integer> {

	@Query("select u from ChatHistory u where u.senderPhone=:senderId and u.receiverPhone=:reciverId")
	public ChatHistory searchPerson(@Param("senderId") String senderId,@Param("reciverId") String reciverId);
	
	@Query("select count(u) as maxid from ChatHistory u where senderPhone=:senderId")
	public int chathistoryMaxid(@Param("senderId") String senderId);
	
	
	@Query(value="SELECT auth.name, tests.chat_history.* from tests.auth join tests.chat_history on tests.auth.mobilenumber=tests.chat_history.receiver_phone where tests.chat_history.sender_phone=:senderId",nativeQuery=true)
	public List<ChatHistory> chatHistory(@Param("senderId") String senderId);
	  
	  @Transactional
	  @Modifying
	  @Query("update ChatHistory set lastMessage=:lastmsg where chat_id=:chatid")
	  public int updateLastMessage(@Param("lastmsg")String lastmsg,@Param("chatid") int chatid);
	 
}
