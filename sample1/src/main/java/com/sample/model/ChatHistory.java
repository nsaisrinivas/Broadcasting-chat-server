package com.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "chat_history")
public class ChatHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int chat_id;
	private String senderPhone;
	private String receiverPhone;
	private String lastMessage;
	@Transient
	private String name;
	public ChatHistory() {
		super();
	}
	public ChatHistory(String name, int chat_id, String senderPhone, String receiverPhone, String lastMessage) {
		super();
		this.name = name;
		this.chat_id = chat_id;
		this.senderPhone = senderPhone;
		this.receiverPhone = receiverPhone;
		this.lastMessage = lastMessage;
	}
	public ChatHistory(int chat_id, String senderPhone, String receiverPhone, String lastMessage) {
		super();
		this.chat_id = chat_id;
		this.senderPhone = senderPhone;
		this.receiverPhone = receiverPhone;
		this.lastMessage = lastMessage;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getSenderPhone() {
		return senderPhone;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getLastMessage() {
		return lastMessage;
	}
	public Integer setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
		return 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
