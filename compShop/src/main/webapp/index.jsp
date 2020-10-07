<%@include file="/WEB-INF/jspf/format.jspf" %>
<%@include file="/WEB-INF/jspf/jspInput.jspf" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ taglib uri="/WEB-INF/tld/custom-functions.tld" prefix="myfn" %>
<%@ page import="com.epam.anatolii.ageev.constants.WebConstant" %>
<%--<jsp:useBean id="cons" scope="session" type="com.epam.anatolii.ageev.constants.WebConstant"/>--%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop Homepage - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="styles/shop-homepage.css" rel="stylesheet">
    <link href="styles/nav-bar.css" rel="stylesheet">
    <link rel="stylesheet" href="styles/register/style.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="js/products.js"></script>
</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="registration">Register</a>
                </li>
            </ul>
        </div>
        <div>
            <ul>${WebConstant.INT_ONE}</ul>
            <ul class="nav navbar-nav navbar-right">
                <tgcp:login/>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <h1 class="my-4">Computer Shop</h1>
            <br>

            <label for="productName">Find by name:</label>
            <input form="filterForm" id="productName" type="text" name="productName"
                   value="${productFilterBean.productName}"><br>

            <hr>
            <div>Category</div>
            <hr>
            <div>
                <c:forEach var="category" items="${categoriesList}">
                    <input form="filterForm" type="checkbox" name="category" value="${category}"
                    <c:if test="${myfn:contains(productFilterBean.categoryNames, category) }"> checked </c:if>
                    > ${category}<br>
                </c:forEach>
            </div>
            <hr>
            <br>
            <hr>
            <div>Producer</div>
            <hr>
            <div>
                <c:forEach var="producer" items="${producersList}">
                    <input form="filterForm" type="checkbox" name="producer" value="${producer}"
                           <c:if test="${myfn:contains(productFilterBean.producerNames,producer)}">checked </c:if>
                    > ${producer}<br>
                </c:forEach>
            </div>
            <hr>
            <div id="price-filter">
                Min price: <input form="filterForm" id="minPrice" type="number" name="minPrice"
                                  value="${productFilterBean.priceMin}"><br>
                Max price: <input form="filterForm" id="maxPrice" type="number" name="maxPrice"
                                  value="${productFilterBean.priceMax}"><br><br>
                <button form="filterForm" type="submit" class="btn btn-primary">Submit</button>
            </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <img class="d-block img-fluid" src="<c:url value="/img/Laptop.jpg"/>" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="<c:url value="/img/Desktop.jpg"/>" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="<c:url value="/img/Server.jpg"/>" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <div>

                <form class="form-inline" id="filterForm" method="GET" action="products">
                    <div class="form-group">
                        <label for="sel1">Sort by:</label>
                        <select class="form-control" id="sel1" name="fieldSort" onchange="this.form.submit()">
                            <option <c:if test="${productFilterBean.sortByField == 'Name'}"> selected </c:if>  >Name
                            </option>
                            <option <c:if test="${productFilterBean.sortByField == 'Price'}"> selected </c:if> >Price
                            </option>
                        </select>
                        <br>
                        <br>
                        <label for="sel2">Sort direction:</label>
                        <select class="form-control" id="sel2" name="sortDirection" onchange="this.form.submit()">
                            <option <c:if test="${productFilterBean.sortDirection == 'ASC'}"> selected </c:if> >ASC
                            </option>
                            <option <c:if test="${productFilterBean.sortDirection == 'DESC'}"> selected </c:if> >DESC
                            </option>
                        </select>
                        <br>
                        <label for="productsPerPage">Per page :</label>
                        <select class="form-control" id="productsPerPage" name="productsPerPage"
                                onchange="this.form.submit()">
                            <option <c:if test="${productFilterBean.productsPerPage == 1}"> selected </c:if> >1</option>
                            <option <c:if test="${productFilterBean.productsPerPage == 10}"> selected </c:if> >10
                            </option>
                            <option <c:if test="${productFilterBean.productsPerPage == 15}"> selected </c:if>>15
                            </option>
                            <option <c:if test="${productFilterBean.productsPerPage == 20}"> selected </c:if> >20
                            </option>
                        </select>
                    </div>

                </form>
            </div>
            <c:choose>
                <c:when test="${productList.size() gt 0}">

                    <tgcp:pagination/>

                    <tgcp:products/>

                    <tgcp:pagination/>

                </c:when>
                <c:otherwise>
                    <h1>No products found</h1>
                </c:otherwise>
            </c:choose>

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->


<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>

</html>
