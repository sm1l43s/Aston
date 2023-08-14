<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Reader</title>
</head>
<body>

<form action="/readers/edit" method="post">
    <input type="hidden" value="${reader.getId()}" name="id">
    <input type="text" placeholder="${reader.getName()}" value="${reader.getName()}" name="name">
    <input type="text" placeholder="${reader.getSurname()}" value="${reader.getSurname()}" name="surname">
    <input type="text" placeholder="${reader.getAddress()}" value="${reader.getAddress()}" name="address">
    <input type="submit" value="Save">
</form>
</body>
</html>
