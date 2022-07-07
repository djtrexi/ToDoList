<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = 'it'>
  <head>
    <meta charset="UTF-8">
    <title>Modifica -- to do</title>
  </head>
  <body>
    <%
      String id = (String) request.getAttribute("id");
      String titolo = (String) request.getAttribute("titolo");
      String testo = (String) request.getAttribute("testo");
      String priorita = (String) request.getAttribute("priorita");
      String data = (String) request.getAttribute("data_scadenza");
      String email= (String)request.getAttribute("email");
    %>
    
    <h1>Modifica task</h1>
    
    <%
      String messaggioModifica = (String) request.getParameter("messaggioModifica");
      if(messaggioModifica == null){
        messaggioModifica = "";
      }
    %>
    
    <h3><%=messaggioModifica%></h3>
    
    
    <div>
      <form action = 'ControlloModificaServlet' method = 'POST'>
        <span>Titolo : <span style = "color: red;">*</span></span>
        <input type = 'text' value = '<%=titolo%>' name = 'titolo_modifica'>
        <br>
        <span>Testo : <span style = "color: red;">*</span></span>
        <input type = 'text' value = '<%=testo%>' name = 'testo_modifica'>
        <br>
        <span>Priorita : <span style = "color: red;">*</span></span>
        <input type = 'text' value = '<%=priorita%>' name = 'priorita_modifica'>
        <br>
        <span>Data : <span style = "color: red;">*</span></span>
        <input type = 'date' value = '<%=data%>' name = 'data_scadenza_modifica'>
        <br>
        <span>(i puntini <span style = "color: red;">*</span> sono campi obbligatori da inserire)</span>
        <br>
        <input type = 'hidden' name = 'email' value = '<%=email%>'>
        <input type = 'hidden' name = 'id' value = '<%=id%>'>
        <input type = 'submit' value = 'modifica'>
        <input type = 'reset' value = 'reset'>
      </form>
    </div>
  </body>
</html>