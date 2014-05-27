package com.ets.t;


import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/testlien") //Vous pouvez utiliser cette annotation pour le mapping au lieu du web.xml
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("date", request.getParameter("param1") + " : " + new Date());
		RequestDispatcher dispatcher = request.getRequestDispatcher("resultat.jsp");
	    dispatcher.forward(request, response);		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
