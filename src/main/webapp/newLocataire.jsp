<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouveau locataire</title>
</head>
<body>
    <h1>Nouveau locataire</h1>
    <form action="saveLocataire" method="post">
        <label for="nom">Nom :</label>
        <input type="text" name="nom" id="nom" required><br><br>

        <label for="prenom">Prénom :</label>
        <input type="text" name="prenom" id="prenom" required><br><br>

        <label for="dateNaissance">Date de naissance :</label>
        <input type="date" name="dateNaissance" id="dateNaissance" required><br><br>

        <label for="adresse">Adresse :</label>
        <input type="text" name="adresse" id="adresse" required><br><br>

        <label for="email">E-mail :</label>
        <input type="email" name="email" id="email" required><br><br>

        <label for="telephone">Téléphone :</label>
        <input type="text" name="telephone" id="telephone" required><br><br>

        <label for="bienLoue">Bien loué :</label>
        <select name="bienLoue" id="bienLoue">
            <option value="">-- Sélectionner un bien --</option>
            <c:forEach items="${biens}" var="bien">
                <option value="${bien.id}">${bien.designation}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Enregistrer">
        <input type="reset" value="Annuler">
    </form>
</body>
</html>