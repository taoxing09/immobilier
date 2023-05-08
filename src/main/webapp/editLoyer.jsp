<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un loyer</title>
</head>
<body>
    <h1>Modifier un loyer</h1>
    <c:url var="updateUrl" value="/updateLoyer" />

    <form action="${updateUrl}" method="post">
        <input type="hidden" name="id" value="${loyer.id}" />

        <label for="datePaiement">Date de paiement :</label>
        <input type="date" id="datePaiement" name="datePaiement" value="<fmt:formatDate value="${loyer.datePaiement}" pattern="yyyy-MM-dd"/>"/><br/>

        <label for="montant">Montant :</label>
        <input type="text" id="montant" name="montant" value="${loyer.montant}" /><br/>

        <label for="locataire">Locataire :</label>
        <select name="locataire.id">
            <option value="">-- Sélectionnez un locataire --</option>
            <c:forEach var="locataire" items="${locataires}">
                <option value="${locataire.id}" <c:if test="${locataire.id == loyer.locataire.id}">selected</c:if>>${locataire.nom} ${locataire.prenom}</option>
            </c:forEach>
        </select><br/>

        <label for="bien">Bien :</label>
        <select name="bien.id">
            <option value="">-- Sélectionnez un bien --</option>
            <c:forEach var="bien" items="${biens}">
                <option value="${bien.id}" <c:if test="${bien.id == loyer.bien.id}">selected</c:if>>${bien.adresse}</option>
            </c:forEach>
        </select><br/>

        <input type="submit" value="Enregistrer"/>
    </form>
</body>
</html>
