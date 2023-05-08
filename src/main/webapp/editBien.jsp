<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier le bien</title>
</head>
<body>
    <h1>Modifier le bien</h1>
    <form action="updateBien" method="post">
        <input type="hidden" name="id" value="${bien.id}">
        <label for="designation">Désignation :</label>
        <input type="text" name="designation" id="designation" value="${bien.designation}" required><br><br>

        <label for="type">Type :</label>
        <input type="text" name="type" id="type" value="${bien.type}" required><br><br>

        <label for="adresse">Adresse :</label>
        <input type="text" name="adresse" id="adresse" value="${bien.adresse}" required><br><br>

        <label for="superficie">Superficie :</label>
        <input type="number" name="superficie" id="superficie" value="${bien.superficie}" required><br><br>

        <input type="submit" value="Enregistrer">
        <input type="reset" value="Annuler">
    </form>
</body>
</html>
