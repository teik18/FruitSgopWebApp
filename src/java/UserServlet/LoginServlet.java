package UserServlet;

import DBUtils.User;
import DBUtils.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         try{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Service</title>");
            out.println("</head>");
            out.println("<body>");
            String userName = request.getParameter("txtUser");
            String password = request.getParameter("txtPass");
            
        if (userName != null && password != null) {
            try {
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.login(userName, password);
                    if (user != null) {
                        request.getSession().setAttribute("username", userName);
                        request.getSession().setAttribute("role", user.isIsAdmin());
                        response.sendRedirect("ShopServlet");
                    } else {
                        out.println("Loggin has failed. <br/>");
                        out.println("<a href='index.html'>Back to login</a>");
                    }
                }
                    catch (Exception ex) {
                    out.println("Something went wrong. Error: " + ex.getMessage());
                }
        }
             
            out.println("</body>");
            out.println("</html>");
        }finally{
           out.close();
       }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
