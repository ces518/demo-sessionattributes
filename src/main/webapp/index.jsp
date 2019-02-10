<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: june
  Date: 2019-02-10
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>event</h3>
    <form:form modelAttribute="eventDTO">
        <form:input path="name"/>
        <form:input path="date"/>
    </form:form>
</body>
</html>
