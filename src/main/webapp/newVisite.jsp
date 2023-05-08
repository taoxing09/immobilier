<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouvelle visite</title>
</head>
<body>
    <h1>Nouvelle visite</h1>
    <form method="post" action="saveVisite.jsp">
        <label for="dateVisite">Date de la visite :</label>
        <input type="date" name="dateVisite" required><br>
        
        <label for="heureVisite">Heure de la visite :</label>
        <input type="time" name="heureVisite" required><br>
        
        <label for="bien">Bien visité :</label>
        <select name="bien">
            <c:forEach items="${biens}" var="bien">
                <option value="${bien.id}">${bien.adresse}</option>
            </c:forEach>
        </select><br>
        
        <label for="locataire">Locataire intéressé :</label>
        <select name="locataire">
            <c:forEach items="${locataires}" var="locataire">
                <option value="${locataire.id}">${locataire.nom} ${locataire.prenom}</option>
            </c:forEach>
        </select><br>
        
        <label for="commentaire">Commentaire :</label>
        <textarea name="commentaire" rows="5"></textarea><br>
        
        <button type="submit">Enregistrer</button>
    </form>
</body>
</html>
