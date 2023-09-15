package com.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int chat_id;
	private String senderPhone;
	private String receiverPhone;
	public Chat() {
		super();
	}
	public Chat(String senderPhone, String receiverPhone) {
		super();
		this.senderPhone = senderPhone;
		this.receiverPhone = receiverPhone;
	}
	public int getChat_id() {
		return chat_id;
	}
	
	public String getSenderPhone() {
		return senderPhone;
	}
	
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
}
