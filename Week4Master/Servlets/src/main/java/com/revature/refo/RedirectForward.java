package com.revature.refo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectForward extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Forwards - RequestDispatcher - resides in our HttpServletRequest
		/*RequestDispatcher rd = req.getRequestDispatcher("hello");
		rd.forward(req, resp);*/
		// Redirects - resides in our HttpServletResponse
		resp.sendRedirect("hello");
	}
}
