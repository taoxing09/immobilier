<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des visites</title>
</head>
<body>
<h1>Liste des visites</h1>
<table>
  <thead>
    <tr>
      <th>ID</th>
      <th>Date</th>
      <th>Heure</th>
      <th>Locataire</th>
      <th>Bien</th>
      <th>Commentaire</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${visites}" var="visite">
    <tr>
      <td>${visite.id}</td>
      <td><fmt:formatDate value="${visite.dateVisite}" pattern="dd/MM/yyyy"/></td>
      <td><fmt:formatDate value="${visite.heureVisite}" pattern="HH:mm"/></td>
      <td>${visite.locataire.nom} ${visite.locataire.prenom}</td>
      <td>${visite.bien.adresse}</td>
      <td>${visite.commentaire}</td>
      <td>
        <a href="editVisite.jsp?id=${visite.id}">Modifier</a>
        <a href="deleteVisite.jsp?id=${visite.id}">Supprimer</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="newVisite.jsp">Ajouter une visite</a>
</body>
</html>
