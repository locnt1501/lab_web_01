<%-- 
    Document   : createNewAccount
    Created on : Aug 9, 2021, 9:31:40 PM
    Author     : LocPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account Page</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatcherController" method="POST">
            Username* <input type="text" name="txtUsername" value="" />(6 - 30 chars)</br>
            Password* <input type="password" name="txtPassword" value="" />(6 - 20 chars)</br>
            Confirm* <input type="password" name="txtPasswordConfirm" value="" /></br>
            First Name* <input type="text" name="txtFirtname" value="" />(3 - 30 chars)</br>
            Last Name* <input type="text" name="txtLastname" value="" />(3 - 30 chars)</br>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
