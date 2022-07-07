package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;

/**
 * Servlet implementation class ModificaServlet
 */
@WebServlet("/ModificaServlet")
public class ModificaServlet extends HttpServlet {
  
  /*@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("WEB-INF/modifica.jsp").forward(request, response);
  }*/

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id = request.getParameter("id");
	String titolo = request.getParameter("titolo");
    String testo = request.getParameter("testo");
    String priorita = request.getParameter("priorita");
    String data_scadenza = request.getParameter("data_scadenza");
    String email = request.getParameter("email");
    
    request.setAttribute("id", id);
    request.setAttribute("titolo", titolo);
    request.setAttribute("testo", testo);
    request.setAttribute("priorita", priorita);
    request.setAttribute("data_scadenza", data_scadenza);
    request.setAttribute("email", email);
    request.getRequestDispatcher("WEB-INF/modifica.jsp").forward(request, response);
  }
}