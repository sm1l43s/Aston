<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="condition"></c:if>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>

<form action="/books/edit" method="post">
    <input type="hidden" name="id" value="${book.getId()}">
    <input type="text" placeholder="${book.getTitle()}" value="${book.getTitle()}"  name="title">
    <input type="text" placeholder="${book.getIsbn()}" value="${book.getIsbn()}" name="isbn">
    <select name="author">
        <c:forEach var="author" items="${authors}">
            <c:choose>
                <c:when test="${book.getAuthor().getId() == author.getId()}">
                    <option value="${author.getId()}" selected>${author.getName()}</option>
                </c:when>
                <c:otherwise>
                    <option value="${author.getId()}">${author.getName()}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <input type="submit" value="Save">
</form>
</body>
</html>
