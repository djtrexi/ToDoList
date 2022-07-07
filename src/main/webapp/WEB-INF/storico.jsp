<%@page import="dao.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.Utente" %>
<%@ page import =  "model.Postit" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "db.Database" %>
<!DOCTYPE html>
<html lang = 'it'>
  <head>
    <style>
      body{
        background-color: #ffa366;
        text-align: center;
      }
      
      table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        background-color: white;
      }
    
      .table{
        margin-top: 10px;
        margin-bottom: 7px;
        text-align: center;
      }
      
      table{
        margin-left: auto;
        margin-right: auto;
      }
    </style>
    <meta charset="UTF-8">
    <%
      String email = (String) request.getAttribute("email");
      Utente u = DaoFactory.getDaoFactory().getUtenteDao().byEmailUtente(email);
      //Database.getInstance().byEmailUtente(email);
    %>
    <title>Storico di <%=u.getNome()%> <%=u.getCognome()%></title>
  </head>
  <body>
    <h1>Storico totale di <%=u.getNome()%> <%=u.getCognome()%></h1>
    <%
      List<Postit> storico = (List<Postit>) request.getAttribute("lista");
    %>
    <div class = 'table'>
      <table>
        <tr>
          <th>Titolo </th>
          <th>Cosa fare </th>
          <th>Priorita </th>
          <th>Scadenza </th>
	    </tr>
    <%
      for(int i = 0; i < storico.size(); i++){
	%>
	    <tr>
	      <td><%=storico.get(i).getTitolo()%></td>
          <td><%=storico.get(i).getTesto()%></td>
          <td><%=storico.get(i).getPriorita()%></td>
          <td><%=storico.get(i).getDataDiScadenza()%></td>
	    </tr>
    <%
      }
    %>
      </table>
    </div>
    
    <div>
      <form action = 'TornaAllaHomeServlet' method = 'POST'>
        <input type = 'hidden' name = 'email' value = '<%=email%>'>
        <input type = 'submit' value = 'torna alla home'>
      </form>
    </div>
  </body>
</html>