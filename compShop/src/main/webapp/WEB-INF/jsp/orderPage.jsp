<%@include file="/WEB-INF/jspf/head.jspf" %>

<link href="<c:url value="/styles/cart.css"/> " rel="stylesheet">
<script src="<c:url value="/js/cart.js"/>"></script>

</head>
<body>

<%@include file="/WEB-INF/jspf/navbar-bar.jspf" %>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Order</h1>
    </div>
</section>

<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col" class="text-center">Quantity</th>
                        <th scope="col" class="text-right">Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cart.getProductsList()}" var="product">
                        <tr id="product-row-${product.id}">
                            <td>${product.name}</td>
                            <td>${cart.getAmountOfProduct(product)}</td>
                            <td class="text-right"
                                id="product-price-${product.id}">${product.price * cart.getAmountOfProduct(product)}
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td><strong>Total</strong></td>
                        <td class="text-right"><strong id="total-price">${cart.getTotalPrice()}</strong></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">

            <form class="form-horizontal" method="post" action="order">
                <fieldset>

                    <!-- Form Name -->
                    <legend>Addition information for order</legend>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="requisites-input">Text Input</label>
                        <div class="col-md-4">
                            <input id="requisites-input" name="requisitesInput" type="text" placeholder="requisites" class="form-control input-md">
                        </div>
                    </div>
                    <div class="row">

                        <div class="col-sm-12  col-md-4 text-center">
                            <a href="products" class="btn-lg btn-block btn-info">Continue Shopping</a>
                        </div>

                        <div class="col-sm-12 col-md-4 text-right">
                            <button class="btn btn-lg btn-block btn-success text-uppercase" type="submit"
                                    >Make an Order
                            </button>
                        </div>
                    </div>
                </fieldset>
            </form>


<%--            <div class="row">--%>



<%--                <div class="col-sm-12  col-md-4 text-center">--%>
<%--                    <a href="products" class="btn-lg btn-block btn-info">Continue Shopping</a>--%>
<%--                </div>--%>

<%--                <div class="col-sm-12 col-md-4 text-right">--%>
<%--                    <button class="btn btn-lg btn-block btn-success text-uppercase"--%>
<%--                            onclick="makeOrder(${loginUser})">Make an Order--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/jspf/footer.jspf" %>
