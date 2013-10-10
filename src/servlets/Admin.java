package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dao.SetupDao;

/**
 * Servlet implementation class Admin
 */
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("do") != null){
			String method = request.getParameter("do");
			
			if(method.equals("clear_data")){
				clear();
			} else if(method.equals("insert_data")){
				insert();
			}
			
			response.sendRedirect("Search");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void clear(){
		new Dao().flushDataDb();
	}
	
	private void insert(){
		new SetupDao().insertTestData();
	}

}
