package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import model.Utente;

/**
 * Servlet Filter implementation class UtenteLoginFilter
 */
@WebFilter("/UtenteLoginFilter")
public class UtenteLoginFilter extends HttpFilter implements Filter {

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request2 = (HttpServletRequest) request;
    HttpServletResponse response2 = (HttpServletResponse) response;
    Utente u = (Utente) request2.getSession().getAttribute("utente");
    if(u != null) {
      chain.doFilter(request2, response2);	
    }
    else {
      response2.sendRedirect("index.jsp");
    }
  }
}