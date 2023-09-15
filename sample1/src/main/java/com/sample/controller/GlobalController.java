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
import com.sample.services.GlobalService;

@Controller
public class GlobalController {
	private static Logger logger=Logger.getLogger(GlobalController.class);
	
	/*
	 * configuring the logger */
	public GlobalController() {
		super();
		BasicConfigurator.configure();
	}

	@Autowired
	private GlobalService globalservice;
	
	/*Fetching tha latest messages that are inserted to the database
	 * using the message id */
	
	@PostMapping("/GlobalController")
	public void chatroom(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int maxid = globalservice.getMaxid();
		int currentId = Integer.parseInt(request.getParameter("currentId"));
		PrintWriter out = response.getWriter();
		String result = "";
		if (maxid > currentId) {
			result = globalservice.getnewMessage(currentId, maxid);
		}
		out.write(result);
	}
	
	/*Getting the userdetails using sessions
     * inserting that messsages to the global table 
     * using the userdetails    */
	@SuppressWarnings("unchecked")
	@GetMapping("/GlobalController")
	public void chat(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/plain");
		HttpSession session=request.getSession(false);
		ArrayList<String> userdetails=(ArrayList<String>)session.getAttribute("userdetails");
		String messagee=request.getParameter("message");
		try {
		globalservice.insertMessage(userdetails.get(0),messagee,userdetails.get(1));
		}catch(BussinessException e) {
			logger.info(e.getErrorMessage());
		}
		logger.info("message sent");
	}
	
	/*
	 * Redirecting to the private chat*/
	@GetMapping("/privatechat")
	public String privateChat() {
		logger.info("Redirecting to Private Chat");
		return "privatechat";
	}
}
