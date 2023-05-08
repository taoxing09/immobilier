<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://java.sun.com/jsp/jstl/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier la visite</title>
</head>
<body>
    <h1>Modifier la visite</h1>
    <form:form method="POST" modelAttribute="visite" action="${pageContext.request.contextPath}/visites/update">
        <input type="hidden" name="id" value="${visite.id}"/>
        
        <p>
            <label for="bien">Bien :</label>
            <form:select path="bien.id">
                <form:option value="">Sélectionner un bien</form:option>
                <c:forEach var="bien" items="${biens}">
                    <form:option value="${bien.id}" ${visite.bien.id == bien.id ? 'selected' : ''}>${bien.adresse}</form:option>
                </c:forEach>
            </form:select>
            <form:errors path="bien.id" cssClass="error"/>
        </p>
        
        <p>
            <label for="locataire">Locataire :</label>
            <form:select path="locataire.id">
                <form:option value="">Sélectionner un locataire</form:option>
                <c:forEach var="locataire" items="${locataires}">
                    <form:option value="${locataire.id}" ${visite.locataire.id == locataire.id ? 'selected' : ''}>${locataire.nom} ${locataire.prenom}</form:option>
                </c:forEach>
            </form:select>
            <form:errors path="locataire.id" cssClass="error"/>
        </p>
        
        <p>
            <label for="dateVisite">Date :</label>
            <form:input path="dateVisite" type="date" value="${visite.dateVisite}">
            </form:input>
            <form:errors path="dateVisite" cssClass="error"/>
        </p>
        
        <p>
            <label for="heureVisite">Heure :</label>
            <form:input path="heureVisite" type="time" value="${visite.heureVisite}">
            </form:input>
            <form:errors path="heureVisite" cssClass="error"/>
        </p>
        
        <p>
            <label for="commentaire">Commentaire :</label>
            <form:textarea path="commentaire" rows="5" cols="50">${visite.commentaire}</form:textarea>
            <form:errors path="commentaire" cssClass="error"/>
        </p>
        
        <p>
            <input type="submit" value="Enregistrer"/>
        </p>
    </form:form>
</body>
</html>
