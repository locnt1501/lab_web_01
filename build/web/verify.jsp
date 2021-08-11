<%-- 
    Document   : verify
    Created on : Aug 11, 2021, 4:51:20 PM
    Author     : LocPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
    </head>
    <body>
        <span>We already send a verification code to your email.com</span>
        <form action="DispatcherController" method="POST">
            <input type="text" name="authcode" value="" />
            <input type="submit" value="Verify" name="btAction" />
        </form>
        <c:set var="errorverify" value="${requestScope.ERRORVERIFY}" />
        <c:if test="${not empty errorverify}">
            <font color="red">
            ${errorverify}
            </font>
        </c:if></br>


    </body>
</html>
