<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Reader</title>
</head>
<body>

<form action="/readers/add" method="post">
    <input type="text" placeholder="Name" name="name">
    <input type="text" placeholder="Surname" name="surname">
    <input type="text" placeholder="Address" name="address">
    <input type="submit" value="Add">
</form>
</body>
</html>
