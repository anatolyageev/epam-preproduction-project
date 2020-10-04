<%@ page import="com.epam.anatolii.ageev.constants.WebConstant" %>
<%@include file="/WEB-INF/jspf/head.jspf" %>

</head>

<body>

<%@include file="/WEB-INF/jspf/navbar-bar.jspf" %>

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
                            <option <c:if
                                    test="${productFilterBean.productsPerPage == WebConstant.INT_ONE}"> selected
                            </c:if> >${WebConstant.INT_ONE}</option>
                            <option <c:if
                                    test="${productFilterBean.productsPerPage == WebConstant.INT_TEN}"> selected
                            </c:if> >${WebConstant.INT_TEN}
                            </option>
                            <option <c:if
                                    test="${productFilterBean.productsPerPage == WebConstant.INT_FIFTEEN}"> selected
                            </c:if>>${WebConstant.INT_FIFTEEN}
                            </option>
                            <option <c:if
                                    test="${productFilterBean.productsPerPage == WebConstant.INT_TWENTY}"> selected
                            </c:if> >${WebConstant.INT_TWENTY}
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

<<<<<<< HEAD
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
=======
<%@include file="/WEB-INF/jspf/footer.jspf" %>
>>>>>>> 9161bfa... Added Cart, Order, ProductsInOrder beans
