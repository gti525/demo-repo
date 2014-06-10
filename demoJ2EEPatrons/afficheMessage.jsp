<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="demo.vue.MessageCouleurViewHelper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>

<%@ page import="demo.modele.BeanMessage" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GTItter</title>

<% ArrayList listeMessages = (ArrayList)request.getAttribute("listeMessages"); %>

<style>
.boite
{
border:solid 1px #DEDEDE;
padding:4px;
font-size: 24px;
}

.normal{
background:#EFEFEF;
font-family: "Verdana";
}

.joyeux{
background-image:url("gradient.png");
background-repeat:repeat-x;
font-family: "Comic Sans MS";
}

.triste{
background:#000000;
color:grey;
font-family: "Courier New";
}

em{
font-size:16px;
}
</style>

</head>
<body>
	<% if (listeMessages != null && listeMessages.size() > 0){
		for (int i=0;i<listeMessages.size(); i++){ 
		BeanMessage message = (BeanMessage)listeMessages.get(i);
	%>
		<div class="boite <%=message.getStyle()%>">
			<%if (message.getStyle().equals("joyeux")){ 
				out.print(MessageCouleurViewHelper.genererMessageColore(message.getMessage()));
			}else{
				out.print(message.getMessage());
			}
			
			
			
			%>
			&nbsp;&nbsp;&nbsp;<em>par <%=message.getNom()%> le <%=DateFormat.getDateTimeInstance(
		            DateFormat.SHORT, DateFormat.SHORT).format(message.getDate())%></em>
		</div>
	<%}} %>
	
	<jsp:include page="creerMessage.jsp" />
</body>
</html>