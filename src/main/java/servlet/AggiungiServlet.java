package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import db.Database;
import model.Utente;

/**
 * Servlet implementation class AggiungiServlet
 */
@WebServlet("/AggiungiServlet")
public class AggiungiServlet extends HttpServlet {

  /*@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getRequestDispatcher("WEB-INF/aggiungi.jsp").forward(request, response);
  }*/
	
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email"); 
	
	request.setAttribute("email", email);
    request.getRequestDispatcher("WEB-INF/aggiungi.jsp").forward(request, response);
  }
}