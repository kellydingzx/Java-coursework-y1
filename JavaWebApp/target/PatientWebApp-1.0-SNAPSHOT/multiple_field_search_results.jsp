<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: dingzixuan
  Date: 18/03/2020
  Time: 9:34 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Multiple Field Search Results.</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Search Result</h1>
    <%
        HashMap<String, String> patients =
                (HashMap<String,String>) request.getAttribute("result");
        if (patients.size() !=0)
        {
    %>
    <ul>
        <%
            for (Map.Entry patient : patients.entrySet())
            {
                String id = (String) patient.getKey();
                String href = "/patientDetail.html?colname=" + "ID" + "&patientname=" + id;
                String message = (String) patient.getValue();
        %>
        <li><a href="<%=href%>"><%=id%></a>
        </li>
        <p><%=message%></p>
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
