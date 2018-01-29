package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Login;
import com.revature.data.LoginDao;
import com.revature.data.LoginOracle;

public class LoginServlet extends HttpServlet {
	private static LoginDao ld = new LoginOracle();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")==null) {
			req.getRequestDispatcher("login.html").forward(req,resp);
		} else {
			resp.sendRedirect("home");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		Login login = ld.login(username, password);
		if(login == null) {
			resp.sendRedirect("login");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("user", login);
			resp.sendRedirect("home");
		}
	}	
}
