package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String htmlresponse = "<html>";
		htmlresponse += "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Home Page</title>\r\n" + " <link rel=\"stylesheet\" href=\"static/css/home.css\"> </head>";
		htmlresponse += "<h2>Welcome.</h2>";
		htmlresponse += "</html>";
		pw.println(htmlresponse);
	}
}
