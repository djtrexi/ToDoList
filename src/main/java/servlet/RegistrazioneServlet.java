package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import db.Database;
import model.Utente;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	request.getSession().invalidate();
	response.sendRedirect("registrazione.jsp");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String nome = request.getParameter("nome_utente");
    String cognome = request.getParameter("cognome_utente");
    String email = request.getParameter("email_utente");
    String password = request.getParameter("password_utente");
    
    if(nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || password.isEmpty()) {
      response.sendRedirect("registrazione.jsp?messaggio=Alcuni campi non sono stati inseriti");
    }
    else {
      Utente u = new Utente(nome, cognome, email, password);
      if(DaoFactory.getDaoFactory().getUtenteDao().registrazione(u) /*Database.getInstance().registrazione(u)*/) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
      }
      else {
        response.sendRedirect("registrazione.jsp?messaggio=Utente gia' registrato nell'applicazione");
      }
    }
  }
}
//request.getRequestDispatcher("index.jsp").forward(request, response);