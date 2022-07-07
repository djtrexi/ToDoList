<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "it">
  <head>
    <style>
      body{
        background-color: #ffa366;
        text-align: center;
      }
      
      .form_login{
        margin-top: 10px;
        text-aling: center;
      }
      
      .link_registrazione{
        text-align: center;
        margin-bottom: 5px;
      }
      
      .title_home{
        text-align: center;
        margin-bottom: 7px;
      }
    </style>
	<meta charset="UTF-8">
	<title>Login -- todolist</title>
  </head>
  <body>
    <header>
      <hgroup>
        <div class = 'title_home'>
          <h1>Benvenuto in To do list per te</h1>
        </div>
        <h3>Login</h3>
      </hgroup>
    </header>
     <%
      String messaggio = request.getParameter("messaggio");
      if(messaggio == null){
        messaggio = "";
      }
    %>
                
	<h3><%=messaggio%></h3>
    
    <div class = 'form_login'>
      <form action = 'LoginServlet' method = 'POST'>
        <span>Email :  <span style = "color: red;">*</span></span>
        <input type = 'email' name = 'email_utente'>
        <br><br>
        <span>Password :  <span style = "color: red;">*</span></span>
        <input type = 'password' name = 'password_utente'>
        <br><br>
        <span>(gli <span style = "color: red;">*</span> sono campi obbligatori)</span>
        <br><br>
        <input type = 'submit' value = 'login'>
        <input type = 'reset' value = 'reset'>
      </form>
    </div>
    
    <div class = 'link_registrazione'>
      <p>Altrimenti</p>
      <a href = 'registrazione.jsp'>Registrati qui a "To do list per te"</a>
    </div>
  </body>
</html>