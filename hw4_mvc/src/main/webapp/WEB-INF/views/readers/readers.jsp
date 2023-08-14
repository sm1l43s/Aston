<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Readers</title>
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
<h2>Readers</h2>
<div>
    <a href="/">Back to main page</a>
</div>
<div>
    <a href="/readers/add">Add new reader</a>
</div>

<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Address</th>
        <th>Reads Books</th>
        <th>Action</th>
    </tr>
    <c:forEach var="reader" items="${readers}">
        <tr>
            <td><c:out value="${reader.getName()}"/></td>
            <td><c:out value="${reader.getSurname()}"/></td>
            <td><c:out value="${reader.getAddress()}"/></td>
            <td>${reader.getBooks()}</td>
            <td>
                <div>
                    <a href="/readers/remove/<c:out value="${reader.getId()}"/>">Remove</a>
                </div>
                <div>
                    <a href="/readers/edit/<c:out value="${reader.getId()}"/>">Edit</a>
                </div>
                <div>
                    <a href="/readers/${reader.getId()}/addBook">Add Books for read</a>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
