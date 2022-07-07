package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
 * Servlet implementation class controlloInserimentoServlet
 */
@WebServlet("/ControlloInserimentoServlet")
public class ControlloInserimentoServlet extends HttpServlet {

  /*@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("WEB-INF/aggiungi.jsp").forward(request, response);
  }*/
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String titolo = request.getParameter("titolo");
	String testo = request.getParameter("testo");
	String p1 = request.getParameter("priorita");
	String ds1 = request.getParameter("data_scadenza");
    String email = request.getParameter("email");
	
    Utente u = DaoFactory.getDaoFactory().getUtenteDao().byEmailUtente(email); //Database.getInstance().byEmailUtente(email);
    
    if(titolo.isEmpty() || testo.isEmpty() || ds1.isEmpty() || p1.isEmpty()) {
      request.setAttribute("email", email);
      request.setAttribute("messaggioInserimento", "Alcuni campi non sono stati inseriti");
      request.getRequestDispatcher("WEB-INF/aggiungi.jsp").forward(request, response);
    }
    else {
      int priorita = Integer.parseInt(p1);
      
      DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  LocalDate dataScadenza = LocalDate.parse(ds1, d);
	    
	  if(dataScadenza.isBefore(LocalDate.now())) {
	    request.setAttribute("email", email);
	    request.setAttribute("messaggioInserimento", "La data non e' corretta");
	    request.getRequestDispatcher("WEB-INF/aggiungi.jsp").forward(request, response);
	  }
	  else {
	    Postit p = new Postit(titolo, testo, dataScadenza, priorita, false, u);
	  
	    if(DaoFactory.getDaoFactory().getPostitDao().aggiungiToDo(p) /*Database.getInstance().aggiungiToDo(p)*/) {
		  u.setTodoList(p);
	      request.setAttribute("lista", DaoFactory.getDaoFactory().getPostitDao().stampaToDoList(u) /*Database.getInstance().stampaToDoList(u)*/);
	      request.setAttribute("utente", u);
	      request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
	    }
	    else {
	      request.setAttribute("email", email);
	      request.setAttribute("messaggioInserimento", "Alcuni campi non sono stati inseriti in modo corretto");
	      request.getRequestDispatcher("WEB-INF/aggiungi.jsp").forward(request, response);
	    }
      } 
    }
  }
}