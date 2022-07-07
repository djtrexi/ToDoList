<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = 'it'>
  <head>
    <style>
      body{
        text-align: center;
        background-color: #ffa366;
      }
    </style>
    <meta charset="UTF-8">
    <title>Aggiungi -- todolist</title>
  </head>
  <body>
    <%
      String email= (String)request.getAttribute("email");
    %>
    <header>
      <h1>Form di aggiuna to do</h1>
    </header>
	  <%
        String messaggioInserimento = (String) request.getParameter("messaggioInserimento");
        if(messaggioInserimento == null){
          messaggioInserimento = "";
        }
      %>
      <h3><%=messaggioInserimento%></h3>
	  
	  <div class = 'form_aggiungi'>
	    <form action = 'ControlloInserimentoServlet' method = 'POST'>        
          <span>Titolo : <span style = "color: red;">*</span></span>
          <input type = 'text' name = 'titolo'>
          <br><br>
          <span>Testo : <span style = "color: red;">*</span></span>
          <input type = 'text' name = 'testo'>
          <br><br>
          <span>Scadenza : <span style = "color: red;">*</span></span>
          <input type = 'date' name = 'data_scadenza'>
          <br><br>
          <span>Priorita : <span style = "color: red;">*</span></span>
          <input type = 'number' min = '1' max = '3' name = 'priorita'>
          <br>
          <br>
          <span>(gli <span style = "color: red;">*</span> sono campi obbligatori)</span>
          <br>
          <br>
          <input type = 'hidden' name = 'email' value='<%=email%>'>
          <input type = 'submit' value = 'aggiungi'>
          <input type = 'reset' value = 'reset'>
        </form>
    </div>
  </body>
</html>