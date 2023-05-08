<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des locataires</title>
</head>
<body>
<h1>Liste des locataires</h1>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Date de naissance</th>
            <th>Adresse</th>
            <th>Email</th>
            <th>Téléphone</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="locataire" items="${locataires}">
            <tr>
                <td>${locataire.id}</td>
                <td>${locataire.nom}</td>
                <td>${locataire.prenom}</td>
                <td>${locataire.dateNaissance}</td>
                <td>${locataire.adresse}</td>
                <td>${locataire.email}</td>
                <td>${locataire.telephone}</td>
                <td>
                    <a href="editLocataire?id=${locataire.id}">Modifier</a>
                    <a href="deleteLocataire?id=${locataire.id}">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a href="newLocataire">Ajouter un locataire</a>
</body>
</html>
