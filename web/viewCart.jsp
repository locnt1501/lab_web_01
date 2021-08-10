<%-- 
    Document   : viewCart
    Created on : Aug 10, 2021, 8:43:03 AM
    Author     : LocPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Check Out Page</title>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <link href="homeStyle.css" rel="stylesheet">
    </head>
    <body>
        <form action="DispatcherController">
            <div>
                <nav class="navbar navbar-expand-sm navbar-dark ">
                    <h2 class="navbar-brand display-4" style="color: black"> Welcome ${sessionScope.USER.name}</h2>
                    <div class="collapse navbar-collapse">
                        <ul class="navbar-nav ml-auto">
                            <c:set var="user" value="${sessionScope.USER}"/>
                            <c:if test="${empty user}">
                                <li class="nav-item">
                                    <a class="nav-link active" href="loginPage">Login</a>
                                </li>
                            </c:if>

                            <c:if test="${not empty user}">
                                <input type="submit" value="Logout" name="btAction" style="color: black" />
                            </c:if>
                        </ul>
                    </div>
                </nav>
            </div>
            <c:set var="listResourceCart" value="${sessionScope.CART}"/>
            <c:if test="${not empty listResourceCart}">
                <div class="container">
                    <table class="table table-hover table-condensed">
                        <thead>
                            <tr>
                                <th style="width: 10%;">No.</th>
                                <th style="width: 10%;">Title</th>
                                <th style="width: 8%;">Quantity</th>
                                <th style="width: 30%;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <form action="DispatcherController">
                            <c:forEach var="type" items="${listResourceCart}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${type.value.itemName}
                                    </td>
                                    <td>
                                        ${type.value.quantity}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${type.key}"/>
                                    </td>
                                </tr>

                            </c:forEach>
                            <tr>
                                <td>
                                    <input type="submit" value="Remove Items" name="btAction" />
                                </td>
                                <td>
                                    <input type="submit" value="Checkout" name="btAction" />
                                </td>
                            </tr>
                        </form>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${empty listResourceCart}">
                <div class="container">
                    <h4 class="alert alert-danger">No Items</h4> 
                </div>
            </c:if>
        </form>
    </body>
</html>
