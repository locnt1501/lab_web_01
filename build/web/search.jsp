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
        <title>Home Dream Travel Page</title>
        <meta charset="utf-8">
        <meta name="robots" content="noindex, nofollow">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="homeStyle.css" rel="stylesheet">
    </head>
    <body>
        <form action="DispatcherController">
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
            <div class="jumbotron row" style="position: relative;">
                <div class="col-6 text-center">
                    <h1 class="display-2">Resource Manage!</h1>
                </div>
                <div class="col-6" >
                    <div class="row justify-content-center">
                        <div class="card " style="background-color: rgba(0,0,0,0.3);" >
                            <div class="card-body">
                                <h1 class="text text-light text-center">Search</h1>
                                    <div class="form-group">
                                        <label class="text-light">Category</label>
                                        <input class="form-control" placeholder="Category" type="text" name="txtCategory" value="${param.txtAddress}">
                                    </div>
                                    <div class="form-group">
                                        <label class="text-light">Name</label>
                                        <input class="form-control" placeholder="Name" type="text" name="txtName" value="${param.txtAddress}">
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
                </div>
            </div>
        </form> 
        <c:set var="listTourSearch" value="${requestScope.listTourSearch}"/>
        <c:if test="${not empty listTourSearch}">
            <main role="main" class="container">
                <c:set var="AddToCardMsg" value="${requestScope.AddToCardMsg}"/>
                <c:if test="${not empty AddToCardMsg}">
                    <div class="alert alert-success">
                        <strong>Success!</strong> ${AddToCardMsg}
                    </div>
                </c:if>
                <div class="row">
                    <c:forEach var="tourMap" items="${listTourSearch}">
                        <c:set var="tour" value="${tourMap.value}"/>
                        <div class="col-md-4 d-flex" >
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="${tour.imageLink}"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <h5>${tour.name}</h5>
                                    <p>Date Tour: ${tour.dateFrom} to ${tour.dateTo}</p>
                                    <p>Place: ${tour.place}</p>
                                    <p>Price: ${tour.price} USD</p>
                                    <p>Quota: ${tour.quota} tickets</p>
                                    <form action="addToCart" method="POST">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <input type="hidden" name="indexPage" value="${param.indexPage}"/>
                                            <input type="hidden" name="txtAddress" value="${param.txtAddress}"/>
                                            <input type="hidden" name="txtDateFrom" value="${param.txtDateFrom}"/>
                                            <input type="hidden" name="txtDateTo" value="${param.txtDateTo}"/>
                                            <input type="hidden" name="txtPriceFrom" value="${param.txtPriceFrom}"/>
                                            <input type="hidden" name="txtPriceTo" value="${param.txtPriceTo}"/>
                                            <c:if test="${tour.quotaRemaining != 0}">
                                                <button type="submit" class="btn btn-sm btn-success" name="tourId" value="${tourMap.key}">Add to card</button>
                                            </c:if>
                                            <small class="text-muted">${tour.quotaRemaining} tickets left</small>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div> <!-- row.// -->
            </main>
            <nav style="margin-top: 20px; background-color: white;"> <!--Paging-->
                <ul class="pagination justify-content-center">
                    <c:set var="lastPage" value="${requestScope.lastPage}"/>
                    <c:forEach var="page" begin="1" end="${lastPage}">
                        <c:url var="pageReWriting" value="search">
                            <c:param name="txtAddress" value="${param.indexPage}"/>
                            <c:param name="txtDateFrom" value="${param.txtDateFrom}"/>
                            <c:param name="txtDateTo" value="${param.txtDateTo}"/>
                            <c:param name="txtPriceFrom" value="${param.txtPriceFrom}"/>
                            <c:param name="txtPriceTo" value="${param.txtPriceTo}"/>
                            <c:param name="indexPage" value="${page}"/>
                        </c:url>
                        <li class="page-item <c:if test="${param.indexPage == page}">
                            active
                            </c:if>
                            <c:if test="${empty param.indexPage and page == 1}">
                                active
                            </c:if>
                            "><a href="${pageReWriting}" class="page-link">${page}</a></li>
                        </c:forEach>
                </ul>
            </nav> <!--Paging-->
        </c:if>
        <c:if test="${empty listTourSearch}">
            <h4 class="alert alert-danger container">No Result</h4> 
        </c:if>
    </body>
</html>
