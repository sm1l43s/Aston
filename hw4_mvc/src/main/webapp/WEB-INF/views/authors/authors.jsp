<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authors</title>
</head>
<style>
    td {
        border: 1px solid black;
        padding: 10px;
    }

    table tr:nth-child(1) {
        background: black;
        font-weight: bold;
        color: white;
    }
</style>
<body>
<h2>Authors</h2>
<div>
    <a href="/">Back to main page</a>
</div>
<div>
    <a href="/authors/add">Add new Author</a>
</div>

<table>
    <tr>
        <th>Name author</th>
        <th>Action</th>
    </tr>
    <c:forEach var="author" items="${authors}">
        <tr>
            <td><c:out value="${author.getName()}"/></td>
            <td>
                <div>
                    <a href="/authors/remove/<c:out value="${author.getId()}"/>">Remove</a>
                </div>
                <div>
                    <a href="/authors/edit/<c:out value="${author.getId()}"/>">Edit</a>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
