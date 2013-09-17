package osa2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Servlet implementation class TeineCounter
 */
public class TeineCounter extends HttpServlet implements HttpSessionListener {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		//Avatud sessioonide arv 
		pw.println("count: " + getTotalActiveSession());
	}
	
	private static int totalActiveSessions;
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		totalActiveSessions++;
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		totalActiveSessions--;
	}
	public static int getTotalActiveSession() {
		return totalActiveSessions;
	}

}
