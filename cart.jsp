<%@ page import="java.util.List"%>
<%@ page import ="com.ecommerce.model.Product"%>

<%
List<Product> products=(List<Product>)request.getAttribute("products");
%>

<html>
<head>
<title>Cart</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>

body{
    font-family:Arial;
    background:#f1f3f6;
    margin:0;
}



.header{
    background:#2874f0;
    color:white;
    padding:15px;
    font-size:22px;
    text-align:center;
}



.product-container{
    width:70%;
    margin:30px auto;
}



.product-card{
    display:flex;
    align-items:center;
    background:white;
    padding:15px;
    margin-bottom:15px;
    border-radius:6px;
    box-shadow:0 2px 6px rgba(0,0,0,0.1);
}

.product-card img{
    width:100px;
    height:100px;
    object-fit:cover;
    margin-right:20px;
}

.product-name{
    font-weight:bold;
    font-size:18px;
}

.product-price{
    color:green;
    font-weight:bold;
}

.stock{
    color:#555;
}



.order-box{
    width:70%;
    margin:20px auto;
    text-align:right;
}

.order-box button{
    background:#fb641b;
    color:white;
    border:none;
    padding:12px 25px;
    font-size:16px;
    cursor:pointer;
    border-radius:4px;
}

</style>

</head>

<body>

<div class="header">
My Cart
</div>

<div class="product-container">

<%
if(products!=null){
for(Product p:products){
%>

<div class="product-card">

<img src="images/<%=p.getImage()%>">

<div>
<div class="product-name">
<%=p.getName()%>
</div>

<div class="product-price">
<i class="fa fa-inr" aria-hidden="true"></i><%=p.getPrice()%>
</div>

<div class="stock">
Quantity: <%=p.getStocks()%>
</div>
</div>

</div>

<%
}
}
%>

</div>

<div class="order-box">

<h2>Checkout</h2>

<form action="placeOrder" method="post">

<label>Address</label>
<input type="text" name="address" required>

<label>Phone</label>
<input type="text" name="phone" required>

<button type="submit">Place Order</button>

</form>
</div>

</body>
</html>