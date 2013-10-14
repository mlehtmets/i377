package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import db.Unit;
/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Unit> units = new ArrayList<Unit>();
		String activity = request.getParameter("do");
		String searchString = request.getParameter("searchString");
		
		if ("delete".equals(activity)) {
			try {
				new Dao().deleteById(Integer.parseInt(request.getParameter("id")));
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		if (searchString == null) {
			try {
				units = new Dao().getAllUnits();		
			} catch (SQLException e) {e.printStackTrace();}
		
		}
		else{
			try {
				units = new Dao().searchUnit(request.getParameter("searchString"));
			} catch (SQLException e) {e.printStackTrace();}
		}
		request.setAttribute("units", units);
				
				request.getRequestDispatcher("WEB-INF/search.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
