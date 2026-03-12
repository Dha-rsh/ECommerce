<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Order" %>
<%@ page import="com.ecommerce.model.Product" %>

<html>
<head>
<title>My Orders</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<style>

body{
    font-family:Arial, Helvetica, sans-serif;
    background:#f1f3f6;
    margin:0;
    padding:20px;
}

h2{
    text-align:center;
    margin-bottom:30px;
}


.order-card{
    background:white;
    padding:20px;
    margin-bottom:20px;
    border-radius:8px;
    box-shadow:0 2px 8px rgba(0,0,0,0.1);
}



.order-header{
    display:flex;
    justify-content:space-between;
    margin-bottom:15px;
    border-bottom:1px solid #eee;
    padding-bottom:10px;
}

.status{
    padding:5px 10px;
    border-radius:4px;
    background:#2874f0;
    color:white;
    font-size:13px;
}



.product-item{
    display:flex;
    align-items:center;
    gap:15px;
    padding:10px 0;
    border-bottom:1px solid #eee;
}

.product-item:last-child{
    border-bottom:none;
}

.product-item img{
    width:80px;
    height:80px;
    object-fit:cover;
    border-radius:6px;
}

.product-details{
    flex:1;
}

.product-name{
    font-weight:bold;
}

.price{
    color:green;
    font-weight:bold;
}

.qty{
    font-size:14px;
    color:#555;
}

</style>
</head>

<body>
<h2>Your Orders</h2>

<%
List<Order> orders = (List<Order>)request.getAttribute("orders");

if(orders != null){
for(Order o : orders){
%>

<div class="order-card">

<div class="order-header">

<div>
<b>Order ID:</b> <%=o.getId()%><br>
<b>Total:</b> <i class="fa fa-inr"></i> <%=o.getTotalPrice()%>
</div>

<div class="status">
<%=o.getStatus()%>
</div>

</div>

<%
List<Product> items = o.getItems();

if(items != null){
for(Product item : items){
%>

<div class="product-item">

<img src="images/<%=item.getImage()%>">

<div class="product-details">

<div class="product-name">
<%=item.getName()%>
</div>

<div class="qty">
Qty: <%=item.getStocks()%>
</div>

<div class="price">
<i class="fa fa-inr"></i> <%=item.getPrice()%>
</div>

</div>

</div>

<%
}
}
%>

</div>

<%
}
}
%>
</body>
</html>