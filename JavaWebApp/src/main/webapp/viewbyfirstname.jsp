<%--
  Created by IntelliJ IDEA.
  User: dingzixuan
  Date: 16/03/2020
  Time: 10:50 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View all patient details by first name. </title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patient first name start by:</h2>
    <ul>
        <%
            char[] alphabets = (char[]) request.getAttribute("charArray");
            for (char c: alphabets)
            {

                String href = "patientfirstnamesByAlphabet.html?char=" + c;
        %>
        <li><a href="<%=href%>"><%=c%></a>
        </li>
        <% } %>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
