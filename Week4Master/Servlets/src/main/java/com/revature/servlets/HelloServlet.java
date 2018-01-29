package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class HelloServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(HelloServlet.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		log.trace("Handling a post request");
		String color = null;
		color = req.getParameter("color");
		log.trace("The requested color was: "+color);
		PrintWriter pw=resp.getWriter();
		pw.write("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Here is your color</title>\r\n" + 
				"</head>\r\n" + 
				"<body>");
		pw.write("<div><div style=\"background-color:"+color+"\"><h4>Hello World</h4></div></div>");
		pw.write("</body></html>");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Handling a get request");
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Insert title here</title>\r\n" + 
				"</head>\r\n" + 
				"<body>");
		printWriter.write("<div><p>Hello World</p><p>I am a webpage</p> <p>Kind of</p></div>");
		printWriter.write("</body></html>");
	}
	@Override
	public void destroy() {
		log.trace("HelloServlet destroyed!");
		super.destroy();
	}
	@Override
	public void init() throws ServletException {
		log.trace("HelloServlet init!");
		super.init();
	}
	
}
