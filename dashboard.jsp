
<%@ page import="java.util.List"%>
<%@ page import="com.ecommerce.model.Product"%>
<%@ page import="com.ecommerce.model.User"%>

<%
List<Product> products = (List<Product>)request.getAttribute("products");
User u = (User)session.getAttribute("user");
%>

<html>

<head>
<title>Dashboard</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>

body{
    font-family:Arial;
    background:#f1f3f6;
    margin:0;
}



.container{
    display:flex;
}



.sidebar{
    width:220px;
    background:#2874f0;
    color:white;
    height:100vh;
    padding:20px;
}

.user-box{
    text-align:center;
    margin-bottom:30px;
}

.user-box img{
    width:80px;
    height:80px;
    border-radius:50%;
}

.user-name{
    margin-top:10px;
    font-weight:bold;
}

.sidebar a{
    display:block;
    margin:15px 0;
    color:white;
    text-decoration:none;
    font-size:15px;
}

.sidebar a:hover{
    text-decoration:underline;
}



.main{
    flex:1;
    padding:20px;
}



.search-box{
    text-align:center;
    margin:20px;
}

.search-box input{
    padding:8px;
    width:250px;
}

.search-box button{
    padding:8px 15px;
    background:#2874f0;
    color:white;
    border:none;
    cursor:pointer;
}



.product-container{
    display:flex;
    flex-wrap:wrap;
    gap:20px;
    justify-content:center;
}



.product-card{
    width:220px;
    background:white;
    border-radius:8px;
    padding:15px;
    text-align:center;
    box-shadow:0 2px 8px rgba(0,0,0,0.1);
    transition:0.3s;
}

.product-card:hover{
    transform:scale(1.05);
}

.product-card img{
    width:150px;
    height:150px;
    object-fit:cover;
}

.product-name{
    font-size:16px;
    margin:10px 0;
    font-weight:bold;
}

.product-price{
    color:green;
    font-weight:bold;
}


.cart-btn{
    background:#fb641b;
    color:white;
    border:none;
    padding:6px 12px;
    cursor:pointer;
    border-radius:4px;
}


.popup{
    position:fixed;
    top:0;
    left:0;
    width:100%;
    height:100%;
    background:rgba(0,0,0,0.5);
    display:flex;
    justify-content:center;
    align-items:center;
}

.popup-content{
    background:white;
    padding:25px;
    border-radius:8px;
    text-align:center;
}

.popup-content button{
    padding:8px 20px;
    background:#2874f0;
    color:white;
    border:none;
    margin-top:10px;
}

</style>

<script>
function closePopup(){
    document.getElementById("popup").style.display="none";
}
</script>

</head>

<body>

<div class="container">


<div class="sidebar">

<div class="user-box">

<img src="images/user.png">

<div class="user-name">
<%=u.getName()%>
</div>

</div>

<hr>

<a href="dashboard">
<i class="fa fa-home"></i> Home
</a>

<a href="cart">
<i class="fa fa-shopping-cart"></i> View Cart
</a>

<a href="viewOrders">
<i class="fa fa-box"></i> My Orders
</a>

<a href="logout">
<i class="fa fa-sign-out"></i> Logout
</a>

</div>




<div class="main">

<div class="search-box">

<form action="dashboard" method="post">
<input type="text" name="type" placeholder="Search by type">
<button type="submit">Search</button>
</form>

</div>


<div class="product-container">

<%
if(products!=null){
for(Product p:products){
%>

<div class="product-card">

<img src="images/<%=p.getImage()%>">

<div class="product-name">
<%=p.getName()%>
</div>

<div class="product-price">
<i class="fa fa-inr"></i> <%=p.getPrice()%>
</div>

<div>
Stock: <%=p.getStocks()%>
</div>

<br>

<form action="cart" method="post">
<input type="hidden" name="productId" value="<%=p.getId()%>">
<button class="cart-btn" type="submit">Add to Cart</button>
</form>

</div>

<%
}
}
%>

</div>


<%
String orderStatus = request.getParameter("order");

if("success".equals(orderStatus)){
%>

<div id="popup" class="popup">
<div class="popup-content">

<h2>Order Placed Successfully!</h2>

<a href="viewOrders">View Orders</a>
<br><br>

<button onclick="closePopup()">OK</button>

</div>
</div>

<%
}else if("fail".equals(orderStatus)){
%>

<div id="popup" class="popup">
<div class="popup-content">

<h2>Order Not Placed</h2>

<button onclick="closePopup()">OK</button>

</div>
</div>

<%
}
%>

</div>
</div>

</body>
</html>