<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Book for Read</title>
</head>
<body>

<form action="/readers/${readerId}/addBook" method="post">
    <select name="book">
        <c:forEach var="book" items="${books}">
            <option value="${book.getId()}">${book.getTitle()}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Add for read">
</form>
</body>
</html>
