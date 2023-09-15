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

import com.sample.services.PrivateChatService;

@Controller
public class PrivatechatAdd {

	private static Logger logger=Logger.getLogger(PrivatechatAdd.class);
	
	/*
	 * Configuring the logger */
	public PrivatechatAdd() {
		super();
		BasicConfigurator.configure();
	}

	@Autowired
	private PrivateChatService privatechatservice;
	
	/*
	 * getting the receiver id and sender id checking whether the receiver is existing in the database or not 
	 * if exist alert the sender with appropriate message("person added"),if not ("User dosenot exist")
	 * */
	@SuppressWarnings("unchecked")
	@GetMapping("/privatechatAdd")
	public void search(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		PrintWriter out=response.getWriter();
		String reciverId= request.getParameter("reciverId");
		
		HttpSession session=request.getSession();
		ArrayList<String> userdetails=(ArrayList<String>) session.getAttribute("userdetails");
		int result=privatechatservice.privateChatAdd(userdetails.get(1),reciverId);
		if(result==0) {
			out.println("User Do not Exist");
			logger.info("User Not Exist");
		}
		else if(result==1) {
			out.println("User Already Exist");
			logger.info("User Exist");
		}
		else {
			out.println("Person Added");
			logger.info("User Added");
		}
	}
	/*
	 * Fetching the last message between the sender and receiver conversation from the chat history table in the database.*/
	@SuppressWarnings("unchecked")
	@PostMapping("/privatechatAdd")
	public void history(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		ArrayList<String> userdetails=(ArrayList<String>) session.getAttribute("userdetails");
		PrintWriter out= response.getWriter();
		int currentId=Integer.parseInt(request.getParameter("historyId"));
		int maxid=privatechatservice.chathistoryMaxid(userdetails.get(1));
		String ans="";
		if(maxid>currentId) 
		{ 
			ans= privatechatservice.getHistory(userdetails.get(1));
			
		  }
		out.write(ans);
		 
	
		
	}
}
