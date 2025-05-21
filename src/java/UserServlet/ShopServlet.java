/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package UserServlet;

import DBUtils.Product;
import DBUtils.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name="ShopServlet", urlPatterns={"/ShopServlet"})
public class ShopServlet extends HttpServlet {
   
    private ProductDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String keyword = request.getParameter("keyword");
        
        List<Product> products;
        if(keyword != null && !keyword.isEmpty()) {
            products = dao.searchByName(keyword);
        } else {
            products = dao.getAllProducts();
        }
        
        request.setAttribute("productList", products);
        request.setAttribute("keyword", keyword); // để giữ lại nội dung ô tìm kiếms
        request.getRequestDispatcher("Shop.jsp").forward(request, response);
    }
}
