package com.sample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sample.model.Chat;
import com.sample.model.ChatHistory;
import com.sample.model.RegistrationModel;
import com.sample.repository.ChatRepository;
import com.sample.repository.PrivateChatRepository;
import com.sample.repository.RegistrationRepository;

@Service
public class PrivateChatService {

	@Autowired
	private final PrivateChatRepository privatechatrepository;

	@Autowired
	private final RegistrationRepository registrationrepository;

	@Autowired
	private final ChatRepository chatrepository;

	public PrivateChatService(PrivateChatRepository privatechatrepository,
			RegistrationRepository registrationrepository, ChatRepository chatrepository) {
		super();
		this.privatechatrepository = privatechatrepository;
		this.registrationrepository = registrationrepository;
		this.chatrepository = chatrepository;
	}

	/*
	 * Getting the sender id and receiver id from controller layer and checking whether the receiver is present in the
	 * users table or not and check whether the conversation has established between the sender and receiver if not create
	 * a new conversation between the sender and receiver.*/
	
	public int privateChatAdd(String senderId, String reciverId) throws NullPointerException {
		RegistrationModel registrationmodel2;
		RegistrationModel registrationmodel1 = new RegistrationModel();
		ChatHistory ch = privatechatrepository.searchPerson(senderId, reciverId);
		if (ch == null) {
			registrationmodel1.setMobilenumber(null);
		}
		if (ch != null) {
			String receiverphone = ch.getReceiverPhone();
			registrationmodel1.setMobilenumber(receiverphone);
		}
		registrationmodel2 = registrationrepository.userExistorNot(reciverId);
		if (registrationmodel2 == null) {
			return 0;
		}
		if (registrationmodel1.getMobilenumber() == null && registrationmodel2.getMobilenumber() != null) {
			Chat chat = new Chat(senderId, reciverId);
			chatrepository.save(chat);
			int chatid = chatrepository.getChatId(senderId, reciverId);
			ChatHistory c1 = new ChatHistory(chatid, senderId, reciverId, "");
			ChatHistory c2 = new ChatHistory(chatid, reciverId, senderId, "");
			privatechatrepository.save(c1);
			privatechatrepository.save(c2);
			return 2;
		} else if (registrationmodel1.getMobilenumber() != null) {
			return 1;
		}
		return 0;
	}

	/*
	 * Getting the maxid of the conversation of the sender */
	
	public int chathistoryMaxid(String senderId) {
		return privatechatrepository.chathistoryMaxid(senderId);
	}

	/*
	 * get all the established conversations of the sender and receivers .*/
	public String getHistory(String senderId) {
		List<ChatHistory> list = privatechatrepository.chatHistory(senderId);
		int i=0;
		for (ChatHistory chatHistory : list) {
			List<String> m = registrationrepository.finbymobilenumber(senderId);
			chatHistory.setName(m.get(i));
			i++;
		}
		Gson json = new Gson();
		return json.toJson(list);
	}

}
