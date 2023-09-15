package com.sample.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
@Component
public class GlobalFilter implements Filter{

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest req=(HttpServletRequest) request;
			HttpServletResponse res=(HttpServletResponse) response;
			HttpSession session=req.getSession();
			ArrayList<String> userdetails=(ArrayList<String>) session.getAttribute("userdetails");
			String name=null;
			String error="error";
			try{
				name=userdetails.get(0);
				
				if(!"".equals(name) && name!=null) {
					
					session.removeAttribute(error);
					chain.doFilter(request, response);}
					else {
						session.setAttribute(error, "pleaseLogin");
						res.sendRedirect("/index");
					}
			}
			catch(NullPointerException e) {
				session.setAttribute(error, "please Login");
			       res.sendRedirect("/index"); 
			}
		
	}

}
