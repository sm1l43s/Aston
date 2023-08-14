<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Author</title>
</head>
<body>

<form action="/authors/add" method="post">
    <input type="text" placeholder="${author.getName()}" name="name">
    <input type="submit" value="Add">
</form>
</body>
</html>
