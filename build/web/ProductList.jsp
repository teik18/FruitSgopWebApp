<%-- 
    Document   : ProductList
    Created on : May 20, 2025, 10:57:14 PM
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
        <title>Product List</title>
        <style>
            table {
                width: 80%;
                border-collapse: collapse;
                margin: 20px auto;
            }
            th, td {
                padding: 8px 12px;
                border: 1px solid #ccc;
                text-align: center;
            }
            th {
                background-color: #f2f2f2;
            }
            .top-nav {
                text-align: center;
                margin-top: 20px;
            }
            .top-nav a {
                margin: 0 10px;
            }
        </style>
    </head>
    <body>
        <div class="top-nav">
            <a href="ProductServlet">Product List</a>
            <a href="ProductForm.jsp">Add Product</a>
            <a href="LogoutServlet">Logout</a>
        </div>
        
        <h2 style="text-align:center;">PRODUCT LIST</h2>
        
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Modify</th>
            </tr>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>
                        <a href="ProductServlet?action=edit&id=${p.id}">Edit</a> |
                        <a href="ProductServlet?action=delete&id=${p.id}" onclic k="return confirm('Confirm delete?')">Detele</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
       
    </body>
</html>
