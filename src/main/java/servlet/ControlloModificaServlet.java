package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.icu.text.DateFormat;

import dao.DaoFactory;
import db.Database;
import model.Postit;
import model.Utente;

/**
 * Servlet implementation class ControlloModifica
 */
@WebServlet("/ControlloModificaServlet")
public class ControlloModificaServlet extends HttpServlet {

  /*@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
  }*/
	
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String titolo = request.getParameter("titolo_modifica");
    String testo = request.getParameter("testo_modifica");
    String priorita = request.getParameter("priorita_modifica");
    String data_scadenza = request.getParameter("data_scadenza_modifica");
    
    String id = request.getParameter("id");
    String email = request.getParameter("email");
    
    long idPostit = Long.parseLong(id);
    
    if(titolo.isEmpty() || testo.isEmpty() || priorita.isEmpty() || data_scadenza.isEmpty()) {
      Postit p2 = DaoFactory.getDaoFactory().getPostitDao().byIdPostIt(idPostit); //Database.getInstance().byIdPostIt(idPostit);
    	
      String prioritaInput = Integer.toString(p2.getPriorita());
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
      String data = formatter.format(p2.getDataDiScadenza());
      
      request.setAttribute("id", id);
      request.setAttribute("titolo", p2.getTitolo());
      request.setAttribute("testo", p2.getTesto());
      request.setAttribute("priorita", prioritaInput);
      request.setAttribute("data_scadenza", data);
      request.setAttribute("email", email);
      
      request.setAttribute("messaggioModifica", "Alcuni campi non sono stati inseriti");
      request.getRequestDispatcher("WEB-INF/modifica.jsp").forward(request, response);
    }
    else {
      int prioritaParse = Integer.parseInt(priorita);
      
      DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  LocalDate dataScadenza = LocalDate.parse(data_scadenza, d);
	  
	  if(dataScadenza.isBefore(LocalDate.now())) {
	    Postit p2 = DaoFactory.getDaoFactory().getPostitDao().byIdPostIt(idPostit); //Database.getInstance().byIdPostIt(idPostit);
	    	
	    String prioritaInput = Integer.toString(p2.getPriorita());
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
	    String data = formatter.format(p2.getDataDiScadenza());
	      
	    request.setAttribute("id", id);
	    request.setAttribute("titolo", p2.getTitolo());
	    request.setAttribute("testo", p2.getTesto());
	    request.setAttribute("priorita", prioritaInput);
	    request.setAttribute("data_scadenza", data);
	    request.setAttribute("email", email);	      
	    
	    request.setAttribute("messaggioModifica", "La data non e' corretta");
	    request.getRequestDispatcher("WEB-INF/modifica.jsp").forward(request, response);
	  }
	  else {
		Utente u = DaoFactory.getDaoFactory().getUtenteDao().byEmailUtente(email); //Database.getInstance().byEmailUtente(email);
		Postit p1 = DaoFactory.getDaoFactory().getPostitDao().byIdPostIt(idPostit); //Database.getInstance().byIdPostIt(idPostit);
		Postit p = new Postit(titolo, testo, dataScadenza, prioritaParse, false, u);
	    
	    if(DaoFactory.getDaoFactory().getPostitDao().updateToDo(p) /*Database.getInstance().updateToDo(p)*/) {
	      p.setTesto(testo);
	      p.setTitolo(titolo);
	      p.setPriorita(prioritaParse);
	      p.setDataDiScadenza(dataScadenza);
	      //Database.getInstance().cancellaPostIt(p1);
	      DaoFactory.getDaoFactory().getPostitDao().cancellaPostIt(p1);
	      request.setAttribute("lista", DaoFactory.getDaoFactory().getPostitDao().stampaStorico(u) /*Database.getInstance().stampaToDoList(u)*/);
	      request.setAttribute("utente", u);
	      request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
	    }
	    else {
	      Postit p2 = DaoFactory.getDaoFactory().getPostitDao().byIdPostIt(idPostit); //Database.getInstance().byIdPostIt(idPostit);
	    	
	      String prioritaInput = Integer.toString(p2.getPriorita());
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
	      String data = formatter.format(p2.getDataDiScadenza());
	        
	      request.setAttribute("id", id);
	      request.setAttribute("titolo", p2.getTitolo());
	      request.setAttribute("testo", p2.getTesto());
	      request.setAttribute("priorita", prioritaInput);
	      request.setAttribute("data_scadenza", data);
	      request.setAttribute("email", email);
	        
		  request.setAttribute("messaggioModifica", "Alcuni non stati inseriti in modo corretto");
		  request.getRequestDispatcher("WEB-INF/modifica.jsp").forward(request, response);
	    }
	  }
    }
  }
}