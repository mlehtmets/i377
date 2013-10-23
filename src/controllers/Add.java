package controllers;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Unit;
import dao.Dao;

/**
 * Servlet implementation class Add
 */
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addUnit(request);
		response.sendRedirect("Search"); 
	}
	
	private void addUnit(HttpServletRequest request){
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		
		Unit unit = new Unit();
		unit.setName(name);
		unit.setCode(code);

		try {
			new Dao().addUnit(unit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
