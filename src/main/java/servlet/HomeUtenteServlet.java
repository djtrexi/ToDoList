package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import model.Utente;

/**
 * Servlet implementation class HomeUtenteServlet
 */
@WebServlet("/HomeUtenteServlet")
public class HomeUtenteServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Utente u = null;
    HttpSession session = request.getSession();
    u = (Utente) session.getAttribute("utente");
    
    request.setAttribute("lista", DaoFactory.getDaoFactory().getPostitDao().stampaToDoList(u) /*Database.getInstance().stampaToDoList(u)*/);
	request.setAttribute("utente", u);
    request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
  }
}