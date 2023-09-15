package com.sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sample.exception.BussinessException;
import com.sample.services.ReadPrivateChatService;

@Controller
public class ReadPrivateChat {
	private static Logger logger=Logger.getLogger(ReadPrivateChat.class);
	public ReadPrivateChat() {
		super();
		BasicConfigurator.configure();
	}
	@Autowired
	private ReadPrivateChatService service;
	
	/*
     * getting the chat message and passing the sender and receiver details along with the messages 
     * to the privateChatService(Service package) class to insert into the database.*/
	@SuppressWarnings("unchecked")
	@GetMapping("/ReadPrivatechat")
	public void conversation(HttpServletRequest request ,HttpServletResponse response) {
		response.setContentType("text/plain");
		HttpSession session=request.getSession(false);
		ArrayList<String> userdetails=(ArrayList<String>)session.getAttribute("userdetails");
		try {
		service.insertMessage(userdetails.get(1),request.getParameter("message"),Integer.parseInt(request.getParameter("chatid")));
		}
		catch(BussinessException e) {
			logger.info(e.getErrorMessage());
		}
			logger.debug("message sent from private Chat");
	}
	/*
	 * reading the messages from the chat message table present in the database*/
	@PostMapping("/ReadPrivatechat")
	public void readchat(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String result=service.readMessage(Integer.parseInt(request.getParameter("chatid")), Integer.parseInt(request.getParameter("currentId")));
		out.write(result);
	}
}
