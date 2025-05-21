<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, DBUtils.Cart" %>
<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("Login.html");
        return;
    }
    List<Cart> cart = (List<Cart>) session.getAttribute("cart");
%>
<html>
    <head>
        <title>View Cart</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
        .body {
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
        table {
        width: 80%;
        margin: 30px auto;
        border-collapse: collapse;
        background-color: #fff8f0;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    th, td {
        padding: 15px;
        text-align: center;
        border: 1px solid #ccc;
    }

    th {
        background-color: #f4a261;
        color: white;
        font-size: 18px;
    }

    tr:nth-child(even) {
        background-color: #fdf1e1;
    }

    .total {
        font-weight: bold;
        background-color: #f4a261;
        color: white;
    }
        </style>
    </head>
    <body>
        <div class="header">
        <h1>View Cart </h1>
        <p>Welcome, <strong><%= username %></strong>!</p>
        <p>This is all the item in your cart!</p>
        
    </div>
    

    <%
        if (cart == null || cart.isEmpty()) {
    %>
        <h3>Your cart is empty.</h3>
    <%
        } else {
            double total = 0;
    %>
        <table>
            <tr>
                <th>Product</th>
                <th>Price ($)</th>
                <th>Quantity</th>
                <th>Subtotal ($)</th>
            </tr>
            <%
                for (int i = 0; i < cart.size(); i++) {
                    Cart item = cart.get(i);
                    double subtotal = item.getPrice() * item.getQuantity();
                    total += subtotal;
            %>
            <tr>
                <td><%= item.getName() %></td>
                <td><%= String.format("%.2f", item.getPrice()) %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= String.format("%.2f", subtotal) %></td>
            </tr>
            <% } %>
            <tr>
                <td colspan="3" class="total">Total</td>
                <td colspan="2" class="total">$<%= String.format("%.2f", total) %></td>
            </tr>
        </table>
    <%
        }
    %>
    
    <div class="nav-links">
        <a href="Shop.jsp">Continue Shopping</a>
    </div>
</body>
</html>

