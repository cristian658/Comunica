<%-- 
    Document   : bandeja
    Created on : Jun 16, 2014, 8:40:41 PM
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! BANDEJA <s:property value="%{#session.correo}"></s:property> </h1>
        <a href="<%= request.getContextPath() %>/logoutAction.action">Logout</a>
    </body>
</html>
