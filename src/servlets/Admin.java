package servlets;

import java.io.IOException;
import java.sql.SQLException;

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
			
			if("clear_data".equals(method)){
				try{ 
					new Dao().deleteList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if("insert_data".equals(method)){
				new SetupDao().insertTestData();
			}
			
			response.sendRedirect("Search");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
