package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import db.Database;
import model.Postit;
import model.Utente;

/**
 * Servlet implementation class StoricoUtenteServlet
 */
@WebServlet("/StoricoUtenteServlet")
public class StoricoUtenteServlet extends HttpServlet {

  /*@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("WEB-INF/storico.jsp").forward(request, response);
  }*/
	
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    
    Utente u = DaoFactory.getDaoFactory().getUtenteDao().byEmailUtente(email); //Database.getInstance().byEmailUtente(email);
    List<Postit> storico = DaoFactory.getDaoFactory().getPostitDao().stampaStorico(u); //Database.getInstance().stampaStorico(u);
    
    request.setAttribute("email", email);
    request.setAttribute("lista", storico);
    request.getRequestDispatcher("WEB-INF/storico.jsp").forward(request, response);
  }
}