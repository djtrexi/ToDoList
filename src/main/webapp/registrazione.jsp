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
    <title>Registrazione -- todolist</title>
  </head>
  <body>
    <header>
      <h1>Registrazione</h1>
    </header>
	
	<%
	  String messaggio = request.getParameter("messaggio");
	  if(messaggio == null){
	    messaggio = "";
	  }
	%>
	<h3><%=messaggio%></h3>
	    
    <div class = 'form_registrazione'>
      <form action = 'RegistrazioneServlet' method = 'POST'>
        <span>Nome : <span style = "color: red;">*</span></span>
        <input type = 'text' name = 'nome_utente'>
        <br><br>
        <span>Cognome : <span style = "color: red;">*</span></span>
        <input type = 'text' name = 'cognome_utente'>
        <br><br>
        <span>Email : <span style = "color: red;">*</span></span>
        <input type = 'email' name = 'email_utente'>
        <br><br>
        <span>Password : <span style = "color: red;">*</span></span>
        <input type = 'password' name = 'password_utente'>
        <br>
        <br>
        <span>(gli <span style = "color: red;">*</span> sono campi obbligatori)</span>
        <br>
        <br>
        <input type = 'submit' value = 'Registrati'>
        <input type = 'reset' value = 'reset'>
      </form>
    </div>
  </body>
</html>