<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dingzixuan
  Date: 19/03/2020
  Time: 1:03 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Number of patients in all age ranges.</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patients:</h2>
    <ul>
        <%
            List<String> results = (List<String>) request.getAttribute("ageRangeResults");
            for (String result : results)
            {
        %>
        <p><%=result%></p>
        </li>
        <% } %>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
