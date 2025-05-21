/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package UserServlet;

import DBUtils.Product;
import DBUtils.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author myapa
 */
@WebServlet(name="ProductServlet", urlPatterns={"/ProductServlet"})
public class ProductServlet extends HttpServlet {
    
    private ProductDAO dao;

    public ProductServlet() {
        dao = new ProductDAO();
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "insert":
                    insertProduct(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                default:
                    listProduct(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
    
    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        try {
            double price = Double.parseDouble(priceStr);
            dao.insertProduct(name, price);
            response.sendRedirect("ProductServlet");
        } catch(IOException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("ProductServlet");
        }
    }
    
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            dao.deleteProduct(Integer.parseInt(id));
            response.sendRedirect("ProductServlet");
        } catch(IOException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("ProductServlet");
        }
    }
    
    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> list = dao.getAllProducts();
        request.setAttribute("products", list);
        request.getRequestDispatcher("ProductList.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            Product product = dao.getProductById(Integer.parseInt(id));
            request.setAttribute("product", product);
            request.getRequestDispatcher("ProductForm.jsp").forward(request, response);
        } catch(ServletException | IOException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("ProductServlet");
        }
    }
    
    private void updateProduct (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        
        try {
            Product p = new Product(Integer.parseInt(id), name, Double.parseDouble(price));
            dao.updateProduct(p);
            response.sendRedirect("ProductServlet");
        } catch(NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("ProductServlet");
        }
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
}
