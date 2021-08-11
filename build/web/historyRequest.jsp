<%-- 
    Document   : historyRequest
    Created on : Aug 10, 2021, 7:38:32 PM
    Author     : LocPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Request Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-sm navbar-dark ">
            <h2 class="navbar-brand display-4" style="color: black"> Welcome ${sessionScope.USER.name}</h2>
            <div class="collapse navbar-collapse">
                <form action="DispatcherController">
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
                </form>
            </div>
        </nav>
        <c:set var="listBookingHistory" value="${sessionScope.LISTBOOKINGHISTORY}" />
        <a href="search.jsp">Back</a>
        <c:if test="${not empty listBookingHistory}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Item Name</th>
                        <th>Category</th>
                        <th>Date Create</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>


                    <c:forEach var="dto" items="${listBookingHistory}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.itemName}</td>
                            <td>${dto.category}</td>
                            <td>${dto.createDate}</td>
                            <td>${dto.status}</td>
                        </tr>
                    </c:forEach>


                </tbody>
            </table>
        </c:if>
        <c:if test="${empty listBookingHistory}">
            <h4 class="alert alert-danger container">No Result</h4> 
        </c:if> 
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    </body>
</html>