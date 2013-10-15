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
		doStuff(request);
		response.sendRedirect("Search");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void doStuff(HttpServletRequest request){
		String behaviour = request.getParameter("do");
		if("insert_data".equals(behaviour)){
			insertDefaultValues();
		}else if("clear_data".equals(behaviour)){
			clear();
		}
	}

	private void clear(){
		Dao uDao = new Dao();
		try {
			uDao.deleteList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertDefaultValues(){
		System.out.println("Adding default values once more");
		SetupDao setup = new SetupDao();
		setup.insertTestData();
	}
}
