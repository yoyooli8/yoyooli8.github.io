package com.ai.frame.test.springmvc.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter extends org.springframework.web.filter.CharacterEncodingFilter{
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestUrl = request.getRequestURI();
//		System.out.println("----------------->"+request.getRequestURI());
		if(requestUrl!=null && requestUrl.endsWith(".html")){
			response.setContentType("text/html;charset=UTF-8");
		}
		
		super.doFilterInternal(request, response, filterChain);
	}
	
	protected void initFilterBean() throws ServletException {
		System.setProperty("file.encoding","utf-8");
	}
}
