<%@ page import="java.sql.*,java.util.*" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.html");
        return;
    }

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Cart</title>
    <meta charset="UTF-8">
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
        table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 80%;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f4a261;
            color: white;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>View Order</h1>
        <p>Welcome, <strong><%= username %></strong>! Here are your orders:</p>
    </div>

    <%
        try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionString = "jdbc:sqlserver://EOKAY\\EXPRESS:1433;database=SampleDB";
        conn = DriverManager.getConnection(connectionString, "sa", "123456"); // Fixed here
        String sql = "SELECT p.name AS product_name, o.quantity AS quantity, p.price as price FROM orders o JOIN Product p ON p.id = o.id WHERE o.username = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        rs = stmt.executeQuery();
    %>  

     <table>
        <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th>Total Price</th>
        </tr>
        <%
            boolean hasOrders = false;
            while (rs.next()) {
                hasOrders = true;
                String productName = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
        %>
        <tr>
            <td><%= productName %></td>
            <td><%= quantity %></td>
            <td>$<%= String.format("%.2f", price * quantity) %></td>
        </tr>
        <% } if (!hasOrders) { %>
        <tr>
            <td colspan="3">No items in cart.</td>
        </tr>
        <% } %>
    </table>

    <%
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignore) {}
            if (stmt != null) try { stmt.close(); } catch (Exception ignore) {}
            if (conn != null) try { conn.close(); } catch (Exception ignore) {}
        }
    %>
</body>
</html>
