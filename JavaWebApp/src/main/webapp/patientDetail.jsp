<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: dingzixuan
  Date: 16/03/2020
  Time: 9:24 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Detail</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patients:</h2>
    <ul>
        <%
            String message = (String) request.getAttribute("patientDetails");
        %>
        <p><%=message%></p>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
