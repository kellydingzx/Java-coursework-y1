<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
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