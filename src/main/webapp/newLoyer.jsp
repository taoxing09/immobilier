<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Nouveau loyer</title>
    </head>
    <body>
        <h1>Nouveau loyer</h1>
        <form action="addLoyer" method="POST">
            <label for="datePaiement">Date de paiement :</label>
            <input type="date" id="datePaiement" name="datePaiement" required><br><br>
            <label for="montant">Montant :</label>
            <input type="number" step="0.01" id="montant" name="montant" required><br><br>
            <label for="locataire">Locataire :</label>
            <select id="locataire" name="locataire" required>
                <c:forEach items="${locataires}" var="locataire">
                    <option value="${locataire.id}">${locataire.nom} ${locataire.prenom}</option>
                </c:forEach>
            </select><br><br>
            <label for="bien">Bien :</label>
            <select id="bien" name="bien" required>
                <c:forEach items="${biens}" var="bien">
                    <option value="${bien.id}">${bien.adresse}</option>
                </c:forEach>
            </select><br><br>
            <label for="dateDebut">Date de début :</label>
            <input type="date" id="dateDebut" name="dateDebut" required><br><br>
            <label for="dateFin">Date de fin :</label>
            <input type="date" id="dateFin" name="dateFin" required><br><br>
            <input type="submit" value="Ajouter">
        </form>
    </body>
</html>
