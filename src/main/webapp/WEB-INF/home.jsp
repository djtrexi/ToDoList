<%@ page language = "java" contentType = "text/html; charset=UTF-8"
    pageEncoding = "UTF-8"%>
<%@ page import = "model.Utente" %>
<%@ page import =  "model.Postit" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html lang = 'it'>
  <head>
    <style>
      body{
        text-align: center;
        background-color: #ffa366;
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
      
      .go_storico{
        margin-top: 10px;
        margin-bottom: 7px;
      }
      
      .go_aggiungi{
        margin-top: 10px;
      }
    </style>
    <meta charset="UTF-8">
    <%
      Utente u = (Utente) session.getAttribute("utente");
    %>
    <title>Home di <%=u.getNome()%> <%=u.getCognome()%></title>
  </head>
  <body>
    <header>
      <hgroup>
        <h1>Salve <%=u.getNome()%></h1>
        <h3>la sua To do list</h3>
      </hgroup>
    </header>        
    <%
      List<Postit> toDoList = new ArrayList<>();
      toDoList = (List<Postit>) request.getAttribute("lista");
    %>
    
    <%
       if(toDoList.size() > 0){
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
          for(int i = 0; i < toDoList.size(); i++){
        %>
            <tr>
              <td><%=toDoList.get(i).getTitolo()%></td>
              <td><%=toDoList.get(i).getTesto()%></td>
              <td><%=toDoList.get(i).getPriorita()%></td>
              <td><%=toDoList.get(i).getDataDiScadenza()%></td>
              <td>
                <%
                  String messaggio = request.getParameter("messaggio");
                  if(messaggio == null){
                    messaggio = "";
                  }
                %>
                
                <h3><%=messaggio%></h3>
                
                <form action = 'EliminaServlet' method = 'POST'>
                  <input type = 'hidden' name = 'idPostIt' value = '<%=toDoList.get(i).getId()%>'>
                  <input type = 'hidden' name = 'email' value = '<%=u.getEmail()%>'>
                  <input type = 'submit' value = 'completato'>
                </form>
              </td> 
              <td>
                <form action = 'ModificaServlet' method = 'POST'>
                  <input type = 'hidden' name = 'id' value = '<%=toDoList.get(i).getId()%>'>
                  <input type = 'hidden' name = 'titolo' value = '<%=toDoList.get(i).getTitolo()%>'>
                  <input type = 'hidden' name = 'testo' value = '<%=toDoList.get(i).getTesto()%>'>
                  <input type = 'hidden' name = 'priorita' value = '<%=toDoList.get(i).getPriorita()%>'>
                  <input type = 'hidden' name = 'data_scadenza' value = '<%=toDoList.get(i).getDataDiScadenza()%>'>
                  <input type = 'hidden' name = 'email' value = '<%=u.getEmail()%>'>
                  <input type = 'submit' value = 'modifica'>
                </form>
              </td>
            </tr>
        <%
          }
        %>
      </table>
    </div>
    <%
       }
       else{
    %>
         <h3>La sua to do list e' vuota</h3>
         <h5>tutti gli obiettivi sono completati</h5>
    <%
       }
    %>
    <div class = 'go_aggiungi'>      
      <form action = 'AggiungiServlet' method = 'POST'>
        <input type = 'hidden' name = 'email' value = '<%=u.getEmail()%>'>
        <input type = 'submit' value = 'aggiungi to do'>
      </form>
    </div>
    
    <%
      if(toDoList.size() != 0){
    %>
    
        <div class = 'go_storico'>
          <form action = 'StoricoUtenteServlet' method = 'POST'>
            <input type = 'hidden' name = 'email' value = '<%=u.getEmail()%>'>
            <input type = 'submit' value = 'guarda storico'>
          </form>
        </div>
    <%
      }
    %>
    <div>
      <form action = 'LogutServlet' method = 'GET'>
        <input type = 'submit' value = 'logut'>
      </form>
    </div>
  </body>
</html>