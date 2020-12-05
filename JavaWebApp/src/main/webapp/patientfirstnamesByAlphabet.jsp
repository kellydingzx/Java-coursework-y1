<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dingzixuan
  Date: 17/03/2020
  Time: 12:03 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View patient first names in alphabetical order. </title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patients:</h2>
    <ul>
        <%
            List<String> patients = (List<String>) request.getAttribute("firstnamesByAlphabet");
            char letter = (char) request.getAttribute("charcter");
            for (String patient : patients)
            {

                String href = "patientDetail.html?colname="+ "FIRST" + "&patientname=" + patient ;
        %>
        <p><%=letter%></p>
        <li><a href="<%=href%>"><%=patient%></a>
        </li>
        <% } %>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>

</body>
</html>
