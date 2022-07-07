package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import db.Database;
import model.Postit;
import model.Utente;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getSession().invalidate();
	response.sendRedirect("index.jsp");
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email_utente");
    String password = request.getParameter("password_utente");
    
    if(email.isEmpty() || password.isEmpty()) {
      response.sendRedirect("index.jsp?messaggio=Alcuni campi non sono stati inseriti");
    }
    else {
      Utente u = DaoFactory.getDaoFactory().getUtenteDao().login(email, password);
      //Database.getInstance().login(email, password);
      if(u == null) {
        response.sendRedirect("index.jsp?messaggio=Email o Password sbagliati");
      }
      else {
    	HttpSession session = request.getSession();
    	session.setAttribute("utente", u);
    	response.sendRedirect("HomeUtenteServlet");
    	
    	//request.setAttribute("lista", DaoFactory.getDaoFactory().getPostitDao().stampaToDoList(u) /*Database.getInstance().stampaToDoList(u)*/);
    	/*request.setAttribute("utente", u);
        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);*/
      }
    }
  }
}