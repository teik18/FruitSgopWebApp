<%-- 
    Document   : ProductForm
    Created on : May 20, 2025, 11:53:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Boolean role = (Boolean) session.getAttribute("role");
    if (session.getAttribute("username") == null || role == null || !role) {
        response.sendRedirect("shop.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Form</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
            }

            h1 {
                text-align: center;
                margin-top: 30px;
                color: #333;
            }

            form {
                max-width: 400px;
                margin: 30px auto;
                padding: 25px 30px;
                background-color: #ffffff;
                border: 1px solid #ddd;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            label {
                display: block;
                margin-top: 15px;
                font-weight: bold;
                color: #555;
            }

            input[type="text"],
            input[type="number"] {
                width: 100%;
                padding: 8px 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                width: 100%;
                padding: 10px;
                margin-top: 20px;
                background-color: #28a745;
                border: none;
                color: white;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <h1>${product != null ? "Update Product" : "Add Product"}</h1>
        
        <form action="ProductServlet" method="post">
            <input type="hidden" name="action" value="${product != null ? "update" : "insert"}">
            
            <c:if test="${product != null}">
                <label>ID:</label>
                <input type="text" name="id" value="${product != null ? product.id : ""}"/>
                <br>
            </c:if>
                
            <label>Name:</label>
            <input type="text" name="name" value="${product != null ? product.name : ""}" required/>
            <br>

            <label>Price:</label>
            <input type="number" step="any" name="price" value="${product != null ? product.price : ""}" required/>
            <br>
            
            <input type="submit" value="${product != null ? "update" : "insert"}">
        </form>
    </body>
</html>
