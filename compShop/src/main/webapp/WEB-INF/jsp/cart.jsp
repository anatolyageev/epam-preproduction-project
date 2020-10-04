<%@include file="/WEB-INF/jspf/head.jspf" %>

<link href="/styles/cart.css" rel="stylesheet">

</head>
<body>


<%@include file="/WEB-INF/jspf/navbar-bar.jspf"%>

<%--<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">--%>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Your Cart</h1>
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
                        <th> </th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${cart.getProductsList()}" var="product">
                    <tr>
                        <td>${product.name}</td>
                        <td><input class="form-control" type="text" value="${cart.getAmountOfProduct(product)}" /></td>
                        <td class="text-right">${product.price * cart.getAmountOfProduct(product)}</td>
                        <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                    </tr>
                    </c:forEach>

                    <tr>

                        <td></td>

                        <td></td>
                        <td><strong>Total</strong></td>
                        <td class="text-right"><strong>${cart.getTotalPrice()}</strong></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                    <a href="products" class="btn btn-block btn-light">Continue Shopping</a>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <button class="btn btn-lg btn-block btn-success text-uppercase">Checkout</button>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/jspf/footer.jspf"%>
