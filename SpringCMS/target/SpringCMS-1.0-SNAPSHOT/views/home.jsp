<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: karol
  Date: 07.01.19
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <style>
        table, th, td {
            border: 2px solid black;
        }
    </style>
</head>
<body>

<h1>Ostatnie 5 dodanych artykułów:</h1>
<table>
    <thead>
    <th>Tytuł</th>
    <th>Data utworzenia</th>
    <th>Pierwsze 200 znaków</th>
    </thead>
    <tbody>
    <c:forEach items="${article}" var="item">
        <tr>
            <td>${item.getTitle()}</td>
            <td>${item.getCreated()}</td>
            <td>${item.getContent()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
