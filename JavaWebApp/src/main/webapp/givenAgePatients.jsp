<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: dingzixuan
  Date: 17/03/2020
  Time: 11:59 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patients found by given age range.</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Search Result for given age range.</h1>
    <%
        ArrayList<String> patients =
                (ArrayList<String>) request.getAttribute("patientsgivenRange");
        if (patients.size() !=0)
        {
    %>
    <ul>
        <%
            for (String patient : patients)
            {
                String href = "/patientDetail.html?colname=" + "ID" + "&patientname=" + patient;
        %>
        <li><a href="<%=href%>"><%=patient%></a>
        </li>
        <% }
        } else
        {%>
        <p>Nothing found</p>
        <%}%>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
