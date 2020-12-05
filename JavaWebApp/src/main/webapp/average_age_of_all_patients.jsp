<%--
  Created by IntelliJ IDEA.
  User: dingzixuan
  Date: 19/03/2020
  Time: 12:07 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Average age of all the patients.</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <ul>
        <%
            String result = (String) request.getAttribute("averageAge");
        %>
        <p><%=result%></p>

    </ul>

</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
