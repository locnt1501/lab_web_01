<%-- 
    Document   : search.jsp
    Created on : Aug 9, 2021, 8:41:49 PM
    Author     : LocPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Resource Page</title>
        <meta charset="utf-8">
        <meta name="robots" content="noindex, nofollow">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    </head>
    <body>
        <div>
            <nav class="navbar navbar-expand-sm navbar-dark ">
                <h2 class="navbar-brand display-4" style="color: black"> Welcome ${sessionScope.USER.name}</h2>
                <div class="collapse navbar-collapse">
                    <form action="DispatcherController" >
                        <ul class="navbar-nav ml-auto">
                            <c:set var="user" value="${sessionScope.USER}"/>
                            <c:if test="${empty user}">
                                <li class="nav-item">
                                    <a class="nav-link active" href="loginPage">Login</a>
                                </li>
                            </c:if>

                            <c:if test="${not empty user}">
                                <c:if test="${user.roleId != 1}">
                                    <a href="viewCart.jsp" style="margin-right: 20px">View Cart</a>
                                    <a href="historyRequest.jsp">History</a>
                                </c:if>
                                <input type="submit" value="Logout" name="btAction" style="color: black" />
                            </c:if>
                        </ul>
                    </form>
                </div>
            </nav>
            <c:if test="${user.roleId == 1}">
                <a href="manageProcess.jsp">Back</a>
            </c:if>
            <div class="jumbotron row" style="position: relative;">
                <div class="col-6 text-center">
                    <h1 class="display-2">Search Resource!</h1>
                </div>
                <form class="col-6" action="DispatcherController" >
                    <div class="row justify-content-center">
                        <div class="card " style="background-color: rgba(0,0,0,0.3);" >
                            <div class="card-body">
                                <h1 class="text text-light text-center">Search</h1>
                                <div class="form-group">
                                    <label class="text-light">Category</label>
                                    <input class="form-control" placeholder="Category" type="text" name="txtCategory" value="${param.txtCategory}">
                                </div>
                                <div class="form-group">
                                    <label class="text-light">Name</label>
                                    <input class="form-control" placeholder="Name" type="text" name="txtName" value="${param.txtName}">
                                </div>
                                <div class="row">
                                    <div class="form-group col-6">
                                        <label class="text-light">Date From</label>
                                        <input class="form-control" placeholder="Date From" type="date" name="txtDateFrom" value="${param.txtDateFrom}">
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="text-light">Date To</label>
                                        <input class="form-control" placeholder="Date To" type="date" name="txtDateTo" value="${param.txtDateTo}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Search" name="btAction" class="btn btn-dark btn-block display-3" />
                                </div>                                                         
                            </div>
                        </div> <!-- card.// -->
                    </div> <!-- row.// -->
                </form>
            </div>
        </div> 
        <c:set var="listResourceSearch" value="${requestScope.SEARCHRESULT}"/>
        <c:if test="${not empty listResourceSearch}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Item Name</th>
                        <th>Color</th>
                        <th>Category</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="page" value="${(not empty param.page) ? param.page : 1}" />
                    <c:set var="rowPerPage" value="${requestScope.ROWS_PER_PAGE}"/>
                    <c:forEach var="dto" items="${listResourceSearch}" varStatus="counter">
                    <form>
                        <tr>
                            <td>${(page - 1) * rowPerPage + counter.count}</td>
                            <td>
                                ${dto.itemName}
                                <input type="hidden" name="txtItemName" value="${dto.itemName}" />
                                <input type="hidden" name="txtResourceId" value="${dto.resourceId}" />
                            </td>
                            <td>
                                ${dto.color}
                            </td>
                            <td>
                                ${dto.category}
                            </td>
                            <td>
                                ${dto.quantity}
                            </td>
                            <td>

                                <div class="d-flex justify-content-between align-items-center">
                                    <input type="hidden" name="txtCategory" value="${param.txtCategory}"/>
                                    <input type="hidden" name="txtName" value="${param.txtName}"/>
                                    <input type="hidden" name="txtDateFrom" value="${param.txtDateFrom}"/>
                                    <input type="hidden" name="txtDateTo" value="${param.txtDateTo}"/>
                                    <input type="submit" value="Add to cart" name="btAction" />
                                </div>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <c:set var="pages" value="${requestScope.PAGES}" />
        <nav style="margin-top: 20px; background-color: white;"> <!--Paging-->
            <ul class="pagination justify-content-center">
                <c:forEach var="i" begin="1" end="${pages}">
                    <li class="page-item <c:if test="${i == page}">active</c:if>">
                        <c:url var="urlRewritingPaging" value="DispatcherController">
                            <c:param name="txtCategory" value="${param.txtCategory}"/>
                            <c:param name="txtName" value="${param.txtName}"/>
                            <c:param name="txtDateFrom" value="${param.txtDateFrom}"/>
                            <c:param name="txtDateTo" value="${param.txtDateTo}"/>
                            <c:param name="btAction" value="Search"/>
                            <c:param name="page" value="${i}"/>
                        </c:url>
                        <a href="${urlRewritingPaging}" class="page-link">${i}</a></li>
                    </li>
                </c:forEach>
            </ul>
        </nav> <!--Paging-->
    </c:if>
    <c:if test="${empty listResourceSearch}">
        <h4 class="alert alert-danger container">No Result</h4> 
    </c:if>
</body>
</html>
