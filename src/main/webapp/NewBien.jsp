<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.*"%>
<%@ page import="java.util.*"%> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouveau bien</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        h1 {
            background-color: #f2f2f2;
            color: #333;
            padding: 20px;
            margin: 0;
        }
        form {
            background-color: #fff;
            padding: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"],
        input[type="number"] {
            padding: 10px;
            border: none;
            border-radius: 5px;
            box-shadow: 1px 1px 1px #ccc;
            margin-bottom: 20px;
            width: 100%;
        }
        input[type="submit"],
        input[type="reset"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: left;
            margin-right: 10px;
        }
        input[type="reset"] {
            background-color: #f44336;
        }
        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Nouveau bien</h1>
    <form action="saveBien" method="post">
        <label for="designation">Désignation :</label>
        <input type="text" name="designation" id="designation" required>

    <label for="type">Type :</label>
    <input type="text" name="type" id="type" required>

    <label for="adresse">Adresse :</label>
    <input type="text" name="adresse" id="adresse" required>

    <label for="superficie">Superficie :</label>
    <input type="number" name="superficie" id="superficie" required>

    <input type="submit" value="Enregistrer">
    <input type="reset" value="Annuler">
</form>
</body>
</html>