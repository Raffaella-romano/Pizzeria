<%@page import="java.sql.*"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  int colNumb = (int) request.getAttribute("colNum");
  ResultSetMetaData metaData = (ResultSetMetaData) request.getAttribute("rsMetaData");
  ResultSet rs = (ResultSet) request.getAttribute("rs");
  String tab = (String) request.getAttribute("tab");
  String usernameC = (String) request.getAttribute("username");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta name="description" content="La Saporita">
<meta name=" author" content="Michela Scarpone">
<meta name=" author" content="Raffaella Romano">

<link rel="stylesheet" type="text/css" href="css/page_style.css">
<link rel="stylesheet" type="text/css" href="css/footer.css">
<link rel="stylesheet" href="css/slides.css" type="text/css">
<link rel="stylesheet" href="css/divStyle.css" type="text/css">
<link rel="stylesheet" href="css/tabControlPanel.css" type="text/css">

<meta name="viewport" content="width-device-width, initial-scale-1.0">

<!-- LA GENERAZIONE DINAMICA DEI NOMI DEI CAMPI NON SEMPRE FUNZIONA 
<style>
@media screen and (max-width: 920px) { 
<%
  for (int i =1; i <colNumb+ 1; i++) { 
%> 
  td:nth-of-type (<%=i %>):before {
    content: "<%=metaData.getColumnLabel(i)%>";
  }
<%
}
%>
</style>
 -->
 
<style>
@media screen and (max-width: 920px) {
<%
  if(tab.equals("prodotto")) {    
%>
    td:nth-of-type(1):before{ content:"Codice:"; }
    td:nth-of-type(2):before{ content:"Nome:"; }
    td:nth-of-type(4):before{ content:"Prezzo:"; }
    td:nth-of-type(5):before{ content:"Ingredienti:"; }
    td:nth-of-type(3):before{ content:"Tipo:"; }
<%    
  }
  else if(tab.equals("adminaccount")) {
%>
    td:nth-of-type(1):before{ content:"Username:"; }
    td:nth-of-type(2):before{ content:"Password:"; }
<%
  }
  else if(tab.equals("cliente")) {
%>
    td:nth-of-type(1):before{ content:"Username:"; }
    td:nth-of-type(2):before{ content:"Nome:"; }
    td:nth-of-type(3):before{ content:"Cognome:"; }
    td:nth-of-type(4):before{ content:"Password:"; }
<%
  }else if(tab.equals("ordine")) {
			  %>
			    td:nth-of-type(1):before{ content:"Codice:"; }
			    td:nth-of-type(2):before{ content:"Cliente:"; }
			    td:nth-of-type(3):before{ content:"Prezzo:"; }
			<%
			  }
   
%>
  
</style>


<title>Tabella <%=tab %></title>
</head>
<body>

  <%
  String url = request.getRequestURL().toString();
  String name = (String) session.getAttribute("name");
  String usernameCliente = (String) session.getAttribute("usernameCliente");
  
  int isAdmin = 0;
  int isCliente = 0;
  if(name!=null) {
%>  
<!-- 
  <p><%=name %></p>
  <a href="logout.jsp?link=<%=url %>">Logout</a><br/>
   -->
<%
  try {
    
    isAdmin = (int) session.getAttribute("adminIn");  
    isCliente = (int) session.getAttribute("clienteIn");
    System.out.println(isAdmin);

  }
  catch(Exception e){
    ;
  }
    
%>
<!-- Navbar piccola -->
  <div class="navbar">
  
    <div id="main_menu">
    <label class="toggle" for="toggle">&#9776;</label> <!-- simbolo menu (3 linee orizzontali) -->
    <input class="toggle" id="toggle" type="checkbox">
    <nav>
      <ul id="menu">
        <!-- HOME -->
        <li class="current"><a href="index.jsp"><img src="img/logo.png" alt="Home" class="icon" id="home" ></a></li>
<%
  if(name!=null) {
%>    
        <li style="float: right;"><a>Benvenuto <%=name%></a></li>
        <li style="float: right;"><a href="logout.jsp?link=<%=url %>"> Logout </a></li>
<%
  } 
%>
      </ul>
      </nav>
      
    </div>

  
  </div>

<%
  }
%>
<% if(isAdmin==1){ %>
  <a href="adminPage.jsp">Torna al pannello di controllo amministratore.</a>

  <h2 align="center">Tabella <%= tab%></h2>
	<table align="center" class="tabConPan">
	
<% 

	
String primaryKey="";
int numData = 0;
if(tab.equals("cliente")){ %>
        <tr class="tabConPan">
<tr>
	<th>Username</th>
	<th>Nome</th>
	<th>Cognome</th>
	<th>Password</th>
	<th>Delete</th>
</tr>
<%}  

if(tab.equals("prodotto")){ %>
        <tr class="tabConPan">
<tr>
	<th>Codice</th>
	<th>Nome</th>
	<th>Tipo</th>
	<th>Prezzo</th>
	<th>Ingredienti</th>
	<th>Delete</th>
	
</tr>
<%}  
 if(tab.equals("adminaccount")){ %>
        <tr class="tabConPan">
<tr>
	<th>Username</th>
	<th>Password</th>
	<th>Delete</th>

</tr>
<%}   if(tab.equals("ordine")){%>
 <tr class="tabConPan">
<tr>
	<th>Codice</th>
	<th>Username Cliente</th>
	<th>Prezzo</th>
	<th>Prodotti</th>
	<th>Delete</th>

</tr>
		<%
}
while(rs.next())
{
%>
		        <tr class="tabConPan">
		
       
<%
	
         for(int i = 1; i<=colNumb;i++) {
          if(i==1) {
            primaryKey=rs.getString(i);
          }
         
          
%>
             <td class="tabConPan">
             <%=rs.getString(i)%>
             </td>
			
<%         	  
          }


          
%>
			  <td class="tabConPan"><a href="./DeleteServlet?table=<%=tab%>&primaryKey=<%=primaryKey%>">ELIMINA</a></td>
			</tr>
<% 
    numData++;
%>
<%
}
%>
  </table>
  
  <p>Occurrency number: <%=numData %></p>
<% } else {%>
		<h2>ADMIN DISCONNESSO!</h2>
	<p>Torna alla <a href="index.jsp">home</a> per loggarti.</p>
<%} %>
</body>
</html>