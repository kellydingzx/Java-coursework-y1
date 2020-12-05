<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: dingzixuan
  Date: 18/03/2020
  Time: 11:05 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find the youngest and oldest patients.</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Youngest Patient</h2>
    <ul>
        <%
            ArrayList<String> youngest =
                    (ArrayList<String>) request.getAttribute("youngest");
            for (String patient : youngest)
            {

                String href = "patientDetail.html?colname="+ "ID" + "&patientname=" + patient;
        %>
        <li><a href="<%=href%>"><%=patient%></a>
        </li>
        <% } %>
    </ul>
    <h2>Oldest Patient</h2>
    <ul>
        <%
            ArrayList<String> oldest =
                    (ArrayList<String>) request.getAttribute("oldest");
            for (String patient : oldest)
            {

                String href = "patientDetail.html?colname="+ "ID" + "&patientname=" + patient;
        %>
        <li><a href="<%=href%>"><%=patient%></a>
        </li>
        <% } %>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
