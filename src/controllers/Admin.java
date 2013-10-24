package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Unit;
import dao.UnitDao;
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
		try {
			if(request.getParameter("do").equals("insert_data")){
				new SetupDao().insertTestData();
			} else if(request.getParameter("do").equals("clear_data")) {
					new UnitDao().deleteUnitList();
			} else if (request.getParameter("do").equals("delete")) {
				Long id = Long.parseLong(request.getParameter("id"));
					new UnitDao().deleteUnit(id);
			} else if(request.getParameter("do").equals("view_data")){
				new UnitDao().loadUnit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
}

}
