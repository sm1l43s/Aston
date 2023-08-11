<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Books</title>
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
  <a href="/books/add">Add new book</a>
</div>

<table>
  <tr>
    <th>Title</th>
    <th>ISBN</th>
    <th>Author</th>
    <th>Action</th>
  </tr>
  <c:forEach var="book" items="${books}">
    <tr>
      <td><c:out value="${book.getTitle()}"/></td>
      <td><c:out value="${book.getIsbn()}"/></td>
      <td><c:out value="${book.getAuthor().getName()}"/></td>
      <td>
        <div>
          <a href="/books/remove/<c:out value="${book.getId()}"/>">Remove</a>
        </div>
        <div>
          <a href="/books/edit/<c:out value="${book.getId()}"/>">Edit</a>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
