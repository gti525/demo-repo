<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cartes Netrunner</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="styles.css" rel="stylesheet">
</head>

<body>

 <div class="navbar navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Trier par</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="liste?tri=extension">Extension</a></li>
            <li><a href="liste?tri=faction">Faction</a></li>
            <li><a href="liste?tri=nom">Nom</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    
	<div class="container">
		<c:forEach items="${cartes}" var="carte" varStatus="compte">
			<div class="col-md-4 <c:out value="${carte.faction}" />">
				<c:out value="${carte.nom}" />
				<br />
				<c:out value="${carte.faction}" />
				<br />
				<img src="./<c:out value = "${carte.image}"/>" />
			</div>
		</c:forEach>
	</div>
</body>
</html>
