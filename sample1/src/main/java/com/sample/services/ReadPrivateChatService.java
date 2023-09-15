package com.sample.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sample.exception.BussinessException;
import com.sample.model.ChatMessage;
import com.sample.repository.PrivateChatReadRepository;
import com.sample.repository.PrivateChatRepository;

@Service
public class ReadPrivateChatService {

	@Autowired
	private final PrivateChatReadRepository repository;
	@Autowired
	private final PrivateChatRepository privaterepository;
	
	public ReadPrivateChatService(PrivateChatReadRepository repository, PrivateChatRepository privaterepository) {
		super();
		this.repository = repository;
		this.privaterepository = privaterepository;
	}
	/*
	 * insert the newly entered messages between the corresponding sender and receiver along with the chatid genereated
	 * in the chat table.
	 * */
	public ChatMessage insertMessage(String sender,String message,int chatid) {
		ChatMessage messagemodel=new ChatMessage(sender,message,chatid);
		if("".equals(messagemodel.getMessage())) {
			throw new BussinessException("message is empty");
		}
		repository.save(messagemodel);
		privaterepository.updateLastMessage(message, chatid);
		return messagemodel;
	}
	/*
	 * Read all the messages between the current id and maxid.*/
	
	public String readMessage(int chatid,int currentid) {
		int maxid=repository.privatechatMaxid(chatid);
		if(maxid>currentid) {
			List<ChatMessage> list=repository.readMessage(chatid);
			List<ChatMessage> list1=new ArrayList<>();
			for(int i=currentid;i<list.size();i++) {
				list1.add(list.get(i));
			}
			Gson json=new Gson();
			return json.toJson(list1);
		}
		return "";
	}
}
