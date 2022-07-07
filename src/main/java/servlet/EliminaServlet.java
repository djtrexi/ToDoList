package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class ModificaServlet
 */
@WebServlet("/EliminaServlet")
public class EliminaServlet extends HttpServlet {

  /*@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
  }*/
	
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idp = request.getParameter("idPostIt");
    String email = request.getParameter("email");
    //Utente u = (Utente) session.getAttribute("utente");
    
    long idPostIt = Long.parseLong(idp);
    
    Postit p = DaoFactory.getDaoFactory().getPostitDao().byIdPostIt(idPostIt); //Database.getInstance().byIdPostIt(idPostIt);
    p.setFinito(true);
    
    if(DaoFactory.getDaoFactory().getPostitDao().rimuoviToDo(p) /*Database.getInstance().rimuoviToDo(p)*/) {
      Utente u = DaoFactory.getDaoFactory().getUtenteDao().byEmailUtente(email); //Database.getInstance().byEmailUtente(email);
      request.setAttribute("lista", DaoFactory.getDaoFactory().getPostitDao().stampaStorico(u) /*Database.getInstance().stampaToDoList(u)*/);
      request.setAttribute("utente", u);
      request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
    }
    else {
      response.sendRedirect("WEB-INF/home.jsp?messaggio=Errore di applicazione -- non possiamo aggiornare il to do");
    }
  }
}