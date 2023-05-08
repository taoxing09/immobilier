<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="models.Bien" %>
<%@ page import="models.PDFGenerator" %>
<%@ page contentType="application/pdf" %>
<%
    List<Bien> biens = (List<Bien>) request.getAttribute("biens");
    String filename = "liste_biens.pdf";
    PDFGenerator.generate(biens, filename);
    response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
%>
