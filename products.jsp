<%@ page import="java.util.List"%>
<%@ page import ="com.ecommerce.model.Product"%>
<%@ page import ="com.ecommerce.model.Admin"%>

<%
Admin u=(Admin)session.getAttribute("admin");
List<Product> products=(List<Product>)request.getAttribute("products");
    
%>

<html>
<head>
<title>Admin Dashboard</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<style>

body{
    margin:0;
    font-family:Arial;
    background:#f1f3f6;
}



.container{
    display:flex;
    height:100vh;
}



.sidebar{
    width:220px;
    background:#2874f0;
    color:white;
    padding:20px;
}

.sidebar h2{
    text-align:center;
}

.sidebar button{
    width:100%;
    padding:10px;
    margin-top:15px;
    border:none;
    background:white;
    color:#2874f0;
    font-weight:bold;
    cursor:pointer;
}


.content{
    flex:1;
    padding:20px;
}



.form-box{
    background:white;
    width:400px;
    padding:20px;
    border-radius:6px;
    box-shadow:0 2px 8px rgba(0,0,0,0.1);
}

.form-box input{
    width:100%;
    padding:8px;
    margin:8px 0;
}

.form-box button{
    background:#2874f0;
    color:white;
    border:none;
    padding:10px;
    width:100%;
}



.product-container{
    display:flex;
    flex-wrap:wrap;
    gap:20px;
}



.product-card{
    background:white;
    width:220px;
    padding:15px;
    border-radius:6px;
    box-shadow:0 2px 6px rgba(0,0,0,0.1);
    text-align:center;
}

.product-card img{
    width:150px;
    height:150px;
    object-fit:cover;
}

.product-name{
    font-weight:bold;
}

.product-price{
    color:green;
    font-weight:bold;
}

.btn{
    padding:6px 10px;
    border:none;
    margin:5px;
    cursor:pointer;
    border-radius:4px;
}

.edit{
    background:orange;
    color:white;
}

.delete{
    background:red;
    color:white;
}

.user-box{
    text-align:center;
    margin-bottom:30px;
}

.user-box img{
    width:250px;
    height:150px;
    border-radius:50%;
    margin-bottom:10px;
}

.user-name{
    font-weight:bold;
    margin-bottom:10px;
}

.logout-btn{
    background:red;
    color:white;
    border:none;
    padding:8px 12px;
    cursor:pointer;
    border-radius:4px;
}

</style>

<script>

function showAddProduct(){
    document.getElementById("productList").style.display="none";
   document.getElementById("editForm").style.display="none";
    document.getElementById("addForm").style.display="block";
}

function showProducts(){
    document.getElementById("addForm").style.display="none";
    document.getElementById("editForm").style.display="none";
    document.getElementById("productList").style.display="block";
}
function showEdit(){
    document.getElementById("addForm").style.display="none";
    document.getElementById("productList").style.display="none";
    document.getElementById("editForm").style.display="block";
    
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

<a href="logout">
<button class="logout-btn">Logout</button>
</a>

</div>

<hr>

<button onclick="showProducts()">Products</button>

<button onclick="showAddProduct()">Add Product</button>


</div>


<div class="content">


<div id="addForm" style="display:none">

<div class="form-box">

<h3>Add Product</h3>

<form action="addProduct" method="post" enctype="multipart/form-data">

<label>Product Name</label>
<input type="text" name="name">

<label>Type</label>
<input type="text" name="type">

<label>Price</label>
<input type="text" name="price">

<label>Stocks</label>
<input type="text" name="stock">

<label>Image</label>
<input type="file" name="image">

<button type="submit">Add Product</button>

</form>

</div>

</div>
<%
Product editProduct = (Product)request.getAttribute("product");
%>

<div id="editForm" style="<%= (editProduct!=null) ? "display:block" : "display:none" %>">

<div class="form-box">

<h3>Edit Product</h3>

<form action="editProduct" method="post" enctype="multipart/form-data">


<input type="hidden" name="id" value="<%=editProduct!=null ? editProduct.getId() : "" %>">

<% if(editProduct != null){ %>

<img src="images/<%=editProduct.getImage()%>" width="120">

<% } %>
<label>Change Image</label>
<input type="file" name="image">
<label>Product Name</label>
<input type="text" name="name" value="<%=editProduct!=null ? editProduct.getName() : "" %>">
<label>Type</label>
<input type="text" name="type" value="<%=editProduct!=null ? editProduct.getType() : "" %>">
<label>Price</label>
<input type="text" name="price" value="<%=editProduct!=null ? editProduct.getPrice() : "" %>">
<label>Stocks</label>
<input type="text" name="stocks" value="<%=editProduct!=null ? editProduct.getStocks() : "" %>">

<button type="submit">Update Product</button>

</form>

</div>

</div>

<div id="productList">

<h2>Products</h2>

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
<i class="fa fa-inr" aria-hidden="true"></i> <%=p.getPrice()%>
</div>

<div>
Stock: <%=p.getStocks()%>
</div>

<br>

<a href="editProduct?id=<%=p.getId()%>">
<button class="btn edit" onclick="showEdit()">Edit</button>
</a>

<a href="deleteProduct?id=<%=p.getId()%>">
<button class="btn delete">Delete</button>
</a>

</div>

<%
}
}
%>

</div>

</div>

</div>

</div>
</div>






</body>
</html>