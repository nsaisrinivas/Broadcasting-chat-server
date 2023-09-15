package com.sample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sample.exception.BussinessException;
import com.sample.model.MessageModel;
import com.sample.repository.MessageRepository;

@Service
public class GlobalService {

	@Autowired
	private final MessageRepository messagerepository;

	public GlobalService(MessageRepository messagerepository) {
		super();
		this.messagerepository = messagerepository;
		
	}
	/*
	 * returns the maxid. */
	
	public int getMaxid() {
		return messagerepository.maxid();
	}
	
	/*
	 * gets the new messages from id and max id and convert them into json format.*/
	
	public String getnewMessage(int currentId,int maxid) {
		List<MessageModel> list=messagerepository.consoleResult(currentId,maxid);
		Gson json=new Gson();
		return json.toJson(list);
	}
	
	/*
	 * insert the messages into the global table.*/
	
	public MessageModel insertMessage(String name,String message,String mobilenumber) {
		MessageModel messagedetails=new MessageModel(name,message,mobilenumber);
		if(messagedetails.getSender()==null||messagedetails.getMessage()==null||messagedetails.getMobilenumber()==null) {
			throw new BussinessException("unable to store message because sender details or message is empty");
		}
		messagerepository.save(messagedetails);
		return messagedetails;
	}
}
