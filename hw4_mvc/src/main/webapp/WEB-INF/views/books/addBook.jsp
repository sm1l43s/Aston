<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>

<form action="/books/add" method="post">
    <input type="text" placeholder="book title" name="title">
    <input type="text" placeholder="isbn" name="isbn">
    <select name="author">
        <c:forEach var="author" items="${authors}">
            <option value="${author.getId()}">${author.getName()}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Add">
</form>
</body>
</html>
