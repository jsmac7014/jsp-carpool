<%--
  Created by IntelliJ IDEA.
  User: jungwoosong
  Date: 11/13/24
  Time: 6:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>
        ${title}
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="icon" href="${pageContext.request.contextPath}/static/favicon.ico?" sizes="any" type="image/x-icon">
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/static/apple-touch-icon.png?">
</head>
<body>
    <jsp:include page="${content}"/>
</body>
</html>
