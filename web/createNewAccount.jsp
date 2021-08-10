<%-- 
    Document   : createNewAccount.jsp
    Created on : Aug 9, 2021, 8:41:49 PM
    Author     : LocPC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Page</title>
        <meta charset="UTF-8">
        <style>
            * {box-sizing: border-box}

            /* Add padding to containers */
            .container {
                padding: 16px;
            }

            /* Full-width input fields */
            input[type=text], input[type=password] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
            }

            input[type=text]:focus, input[type=password]:focus {
                background-color: #ddd;
                outline: none;
            }

            /* Overwrite default styles of hr */
            hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
            }

            /* Set a style for the submit/register button */
            .registerbtn {
                background-color: #04AA6D;
                color: white;
                padding: 16px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
                opacity: 0.9;
            }

            .registerbtn:hover {
                opacity:1;
            }

            /* Add a blue text color to links */
            a {
                color: dodgerblue;
            }

            /* Set a grey background color and center the text of the "sign in" section */
            .signin {
                background-color: #f1f1f1;
                text-align: center;
            }
        </style>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="DispatcherController" method="POST">
            <c:set var="errors" value="${requestScope.CREATEERROR}" />
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>
            <label for="email"><b>Email:</b></label></br>
            <input type="text" placeholder="Enter Email" name="txtEmail" id="email" required style="width: 25%"> </br>

            </br>
            <label for="psw"><b>Password:</b></label></br>
            <input type="password" placeholder="Enter Password" name="txtPassword" id="psw" required style="width: 25%"></br>

            <label for="txtRepeatPassword"><b>Repeat Password </b></label></br>
            <input type="password" placeholder="Repeat Password" name="txtRepeatPassword" id="psw-repeat" required style="width: 25%"></br>

            <label for="txtName"><b>Name</b></label></br>
            <input type="text" placeholder="Name" name="txtName" required style="width: 25%"></br>
            <hr>
            <b>Phone number: </b></br>
            <input type="number" placeholder="Phone number" name="txtPhoneNumber" required>
            </br>
            <label for="txtAddress"><b>Address</b></label></br>
            <input type="text" placeholder="Address" name="txtAddress" required style="width: 25%"></br>
            <hr>
            <c:if test="${not empty errors.confirmNotMatchPassword}">
                <font color="red">
                ${errors.confirmNotMatchPassword}
                </font></br>
            </c:if>

            <c:if test="${not empty errors.emailIsExisted}">
                <font color="red">
                ${errors.emailIsExisted}
                </font></br>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" class="registerbtn" style="width: 25%"/>

            <div class="container signin">
                <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
            </div>
        </form>
    </body>
</html>
