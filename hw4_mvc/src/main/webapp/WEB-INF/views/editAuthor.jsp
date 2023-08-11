<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Author</title>
</head>
<body>

<form action="/authors/edit" method="post">
    <input type="hidden" value="${author.getId()}" name="id">
    <input type="text" value="${author.getName()}" placeholder="${author.getName()}" name="name">
    <input type="submit" value="Save">
</form>
</body>
</html>
