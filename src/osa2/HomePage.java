package osa2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		// Tervitus
		pw.println("Hello<br>");
		
		//Sessiooni id saamine
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String ssnId = session.getId();
		pw.println("Your session id is " + ssnId + "<br>");
		
		//Sessiooni atribuudi seadmine
		pw.println("Session attribute is " + request.getParameter("param") + "<br>");
	}

}
