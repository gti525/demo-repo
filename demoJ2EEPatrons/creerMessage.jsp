<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="demo.vue.MessageCouleurViewHelper" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GTItter</title>

</head>
<body>
	<form method="post" action="nouveauMessage.do">
		Message: <input type="text" name="message" size="20" maxlength="140"><br />
		Nom: <input type="text" name="nom" size="20" maxlength="20"> 		
		Style:<select name="style">
			<option value="normal">Normal</option>
			<option value="joyeux">Joyeux</option>
			<option value="triste">Triste</option>
		</select>
		<br />
		<input type="submit" value="Soumettre">
	</form>
</body>
</html>