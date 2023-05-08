<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modification de locataire</title>
</head>
<body>
    <h1>Modification de locataire</h1>
    <form action="updateLocataire" method="post">
        <input type="hidden" name="id" value="${locataire.id}">
        <label for="nom">Nom :</label>
        <input type="text" name="nom" id="nom" value="${locataire.nom}" required><br><br>

        <label for="prenom">Prénom :</label>
        <input type="text" name="prenom" id="prenom" value="${locataire.prenom}" required><br><br>

        <label for="dateNaissance">Date de naissance :</label>
        <input type="date" name="dateNaissance" id="dateNaissance" value="${locataire.dateNaissance}" required><br><br>

        <label for="adresse">Adresse :</label>
        <input type="text" name="adresse" id="adresse" value="${locataire.adresse}" required><br><br>

        <label for="email">Email :</label>
        <input type="email" name="email" id="email" value="${locataire.email}" required><br><br>

        <label for="telephone">Téléphone :</label>
        <input type="text" name="telephone" id="telephone" value="${locataire.telephone}" required><br><br>

        <label for="bienLoue">Bien loué :</label>
        <select name="bienLoue" id="bienLoue">
            <option value="-1">Sélectionnez un bien</option>
            <c:forEach items="${biens}" var="bien">
                <option value="${bien.id}" ${locataire.bienLoue.id == bien.id ? 'selected' : ''}>${bien.designation}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Enregistrer">
        <input type="reset" value="Annuler">
    </form>
</body>
</html>
