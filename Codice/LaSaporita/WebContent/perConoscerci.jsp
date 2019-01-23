<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*, java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<title>Per conoscerci</title>
</head>
<body>


<%
	String url = request.getRequestURL().toString();
	String name = (String) session.getAttribute("name");
	String usernameCliente = (String) session.getAttribute("usernameCliente");
	
	int isAdmin = 0;
	int isCliente = 0;
	if(name!=null) {

	try {
		isAdmin = (int) session.getAttribute("adminIn");  
		isCliente = (int) session.getAttribute("clienteIn"); 
	}
	catch(Exception e){
		;
	}
		
%>

	
<%
			if(usernameCliente!=null) {
%>
<!-- 		<p>ID negozio: <%=usernameCliente %></p>  -->
<% 
			}
		}
	
%>
<!-- Navbar grande -->
<div class="navbar">
	
	<div id="main_menu">
		<label class="toggle" for="toggle">&#9776;</label> <!-- simbolo del menu (3 linee orizzontali) -->
		<input class="toggle" id="toggle" type="checkbox">
		<nav>
		<ul id="menu">
			<!-- HOME -->
			<li class="current"><a href="index.jsp"><img src="img/logo.png" alt="Home"
					class="icon" id="home"></a></li>

			<!-- PRODOTTI -->
			<li class="has_children"><a href="./AllProductList"> PRODOTTI</a>
				<ul>
					<!-- dropdown menu -->

					<li><a href="./AllProductList?tipo=pizze">Pizze</a></li>

					<li><a href="./AllProductList?tipo=panini">Panini</a></li>

					<li><a href="./AllProductList?tipo=sfizi">Sfizi e Varie</a></li>

					<li><a href="./AllProductList?tipo=bibite">Bibite</a></li>

				</ul></li>
			
			<%
				if (name != null) {
			%>
			<li style="float: right;"><a>Benvenuto <%=name%></a></li>
			<li style="float: right;"><a href="logout.jsp?link=<%=url%>">
					Logout </a></li>
			<%
				} else {
			%>
			<li style="float: right;"><a href="login.jsp?link=<%=url%>">
					Login </a></li>
			<%
				}
			%>
			<li style="float: right;"><a href="CartPage.jsp"><img
					src="img/empty-cart-light.png" alt="Carrello" class="icon"
					id="cartHome"></a></li>



		</ul>
		</nav>
	</div>

	</div>
	
	<h2 align="center">La Saporita</h2>
	<br/>
	<div class="divContorno">
		<div id="contenitore">
		
		<pre class="preDescrizione" style="white-space: pre-wrap; ">
		La pizzeria La Saporita � situato a Carano in provincia di Sessa Aurunca. 
		Consente di effettuare ordini da casa, con ritiro presso il nostro locale. 
		Vi aspettiamo in tanti.
		</pre>
		
		</div>
	</div>
	
	<!-- Footer -->
	
	<footer id="footer">
<hr>
	
	<div id = "info_menu">
		<p><a href="perConoscerci.jsp">Per conoscerci</a> </p>
		<p><a href="contatti.jsp">Contatti</a></p>
<%
		if(isAdmin == 1) { //Admin connesso. Passa a pannello controllo admin
%>
		<p id = "right_side" align="right"><a href="adminPage.jsp">Zona Riservata</a></p>
<%
		} else if(isCliente == 1) { //cliente connesso
%>
		<p id = "right_side" align="right"><a href="adminPage.jsp?link=<%=url %>">Zona Riservata</a></p>
<%
		} else { //nessuno connesso. Porta alla pagina di login
%>
		<p id = "right_side" align="right"><a href="adminPage.jsp?link=<%=url %>">Zona Riservata</a></p>
<%
		}
%>
	</div>
		
	</footer>	
	
	<hr>
	
	<p style="color:grey; text-align:center;">Copyright � 2018 La Saporita. All rights reserved.</p>



</body>
</html>