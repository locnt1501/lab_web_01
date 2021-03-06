<%-- 
    Document   : manageProcess
    Created on : Aug 10, 2021, 4:13:42 PM
    Author     : LocPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Manage Process Page</title>
        <style>
            #manage-table {
                font-family: Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;

            }

            #manage-table td, #manage-table th {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center
            }

            #manage-table tr:nth-child(even){background-color: #f2f2f2;}

            #manage-table tr:hover {background-color: #ddd;}

            #manage-table th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #04AA6D;
                color: white;
                text-align: center
            }
        </style>
    </head>
    <body>
        <div>
            <nav class="navbar navbar-expand-sm navbar-dark ">
                <h2 class="navbar-brand display-4" style="color: black"> Welcome ${sessionScope.USER.name}</h2>
                <div class="collapse navbar-collapse">
                    <form action="DispatcherController">
                        <ul class="navbar-nav ml-auto">
                            <c:set var="user" value="${sessionScope.USER}"/>
                            <c:if test="${empty user}">
                                <li class="nav-item">
                                    <a class="nav-link active" href="login.jsp">Login</a>
                                </li>
                                <c:redirect url="login.jsp"/>
                            </c:if>

                            <c:if test="${not empty user}">
                                <input type="submit" value="Logout" name="btAction" style="color: black" />
                            </c:if>
                        </ul>
                    </form>
                </div>
            </nav>
            <form action="DispatcherController">
                Value: <input type="text" name="txtValue" value="${param.txtValue}" /></br>
                Status:
                <select name="ddList" >
                    <option value="new" ${param.ddList == 'new' ? 'selected="selected"' : '' }>New</option>
                    <option value="delete" ${param.ddList == 'delete' ? 'selected="selected"' : '' }>Delete</option>
                    <option value="accept" ${param.ddList == 'accept' ? 'selected="selected"' : ''}>Accept</option>
                </select>

                </br>
                <input type="submit" value="SearchBooking" name="btAction" class="btn btn-success w-25 mt-3" />
            </form>
            <c:set var="listSearchBooking" value="${requestScope.LISTBOOKINGSEARCH}" />
            <a href="search.jsp">Search Resource</a>
            <c:if test="${not empty listSearchBooking}">
                <table border="1" id="manage-table">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Booking ID</th>
                            <th>Item Name</th>
                            <th>Date Create</th>
                            <th>Date Booking From</th>
                            <th>Date Booking To</th>
                            <th>User Request</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" varStatus="counter" items="${listSearchBooking}" >
                            <c:set var="dtoItemName" value="${dto.listItemName}" />
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.getBookingId()}
                                </td>
                                <td>
                                    <table border="1" style="width: 100%">
                                        <tbody  >
                                            <c:forEach var="item" items="${dtoItemName}">
                                                <tr>
                                                    <td>${item}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </td>
                                <td>
                                    ${dto.getDateCreate()}
                                </td>
                                <td>
                                    ${dto.getDateBookingFrom()}
                                </td>
                                <td>
                                    ${dto.getDateBookingTo()}
                                </td>
                                <td>
                                    ${dto.getEmail()}
                                </td>
                        <form action="DispatcherController">
                            <td>
                                <select name="valueStatus">
                                    <option value="1" ${dto.getStatusId() == 1 ? 'selected="selected"' : ''}>New</option>
                                    <option value="2" ${dto.getStatusId() == 2 ? 'selected="selected"' : ''}>Accept</option>
                                    <option value="3" ${dto.getStatusId() == 3 ? 'selected="selected"' : ''}>Delete</option>
                                </select>  
                            </td>

                            <td>
                                <input type="hidden" name="txtBookingId" value="${dto.getBookingId()}" />
                                <input type="hidden" name="txtValue" value="${param.txtValue}" />
                                <input type="hidden" name="ddList" value="${param.ddList}" />
                                <input type="submit" value="Update" name="btAction" />
                            </td>
                        </form>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:set var="pages" value="${requestScope.PAGES}" />
                <nav style="margin-top: 20px; background-color: white;"> <!--Paging-->
                    <ul class="pagination justify-content-center">
                        <c:forEach var="i" begin="1" end="${pages}">
                            <li class="page-item <c:if test="${i == page}">active</c:if>">
                                <c:url var="urlRewritingPaging" value="DispatcherController">
                                    <c:param name="txtValue" value="${param.txtValue}"/>
                                    <c:param name="ddList" value="${param.ddList}"/>
                                    <c:param name="txtDateFrom" value="${param.txtDateFrom}"/>
                                    <c:param name="txtDateTo" value="${param.txtDateTo}"/>
                                    <c:param name="btAction" value="SearchBooking"/>
                                    <c:param name="page" value="${i}"/>
                                </c:url>
                                <a href="${urlRewritingPaging}" class="page-link">${i}</a></li>
                            </li>
                        </c:forEach>
                    </ul>
                </nav> <!--Paging-->

            </c:if>
            <c:if test="${empty listSearchBooking}">
                <h4 class="alert alert-danger container">No Result</h4> 
            </c:if>
        </div>
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
