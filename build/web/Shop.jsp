<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("Login.html"); // redirect to login if not logged in
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fresh Fruit Shop</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        .header {
            background-color: #f4a261;
            color: white;
            padding: 20px;
        }
        .header input {
            margin-top: 10px;
            padding: 5px;
        }
        .products {
            display: flex;
            justify-content: center;
            gap: 20px;
            padding: 20px;
        }
        .product {
            border: 1px solid #ddd;
            padding: 10px;
            width: 200px;
        }
        .product img {
            max-width: 200px;
            max-height: 150px;
        }
        .product button {
            background-color: #2ecc71;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }
        .footer {
            background-color: #f4a261;
            color: white;
            padding: 10px;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
        .action{
            display: flex;
            justify-content: center;
        }
        .button {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .button input {
            padding: 10px 20px;
            color: white;
            margin: 0 10px;
            background-color: #2ecc71;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Fresh Fruit Shop </h1>
        <p>Healthy & Delicious Fruits Delivered Fresh to You!</p>
        <p>Welcome, <strong><%= username %></strong>!</p>
        
        <div class="action">
            <%  
                Boolean role = (Boolean) session.getAttribute("role");
                if(role != null && role) {
            %>
                <form action="ProductServlet" method="get" style="margin-top: 10px;">
                    <div class="button">
                       <input type="submit" value="Management">
                    </div>
                </form>
            <%
                }
            %>            
            <form action="ViewOrder.jsp" method="get" style="margin-top: 10px;">
                <div class="button">
                   <input type="submit" value="View Order">
                </div>
            </form>

            <form action="Cart.jsp" method="get" style="margin-top: 10px;">
                <div class="button">
                    <input type="submit" value="View Cart">
                </div>
            </form>
            
            <form action="LogoutServlet" method="get" style="margin-top: 10px;">
                <div class="button">
                    <input type="submit" value="Logout">
                </div>
            </form>
        </div>
        
        <form action="ShopServlet" method="get" style="margin-top: 10px;">
            <input type="text" name="keyword" placeholder="Search fruits..." value="${keyword != null ? keyword : ''}">
            <input type="submit" value="Search">
        </form>

    </div>
    <div class="products">
        <c:forEach var="p" items="${productList}">
            <div class="product">
                <form action="ManageCart" method="post">
                    <img src="img/${p.name}.jpg" alt="${p.name}">
                    <h3>${p.name}</h3>
                    <p>$${p.price}</p>
                    <input type="hidden" name="name" value="${p.name}">
                    <input type="hidden" name="price" value="${p.price}">
                    <button>Add to Cart</button>
                </form>
            </div>
        </c:forEach>
    </div>
    <div class="footer">
        <p>© 2025 Fresh Fruit Shop. All Rights Reserved | <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a> | <a href="#">Contact Us</a></p>
    </div>

    <script>
        document.querySelectorAll('.product button').forEach(button => {
            button.addEventListener('click', () => {
                alert('Item added to cart!');
            });
        });
    </script>
</body>
</html>