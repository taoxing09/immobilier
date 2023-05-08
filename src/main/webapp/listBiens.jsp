<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des biens</title>
    <style>
        table {
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
            padding: 5px;
        }
    </style>
</head>
<body>
    <h1>Liste des biens</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Désignation</th>
            <th>Type</th>
            <th>Adresse</th>
            <th>Superficie</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bien" items="${biens}">
            <tr>
                <td>${bien.id}</td>
                <td>${bien.designation}</td>
                <td>${bien.type}</td>
                <td>${bien.adresse}</td>
                <td>${bien.superficie} m²</td>
                <td>
                    <a href="editBien?id=${bien.id}">Modifier</a>
                    |
                    <a href="deleteBien?id=${bien.id}">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="newBien">Ajouter un bien</a>
</body>
</html>
