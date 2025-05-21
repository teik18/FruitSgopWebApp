/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUtils;

import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserDAO {

    // Phương thức để kết nối đến cơ sở dữ liệu
    public Connection getConnection() {
        return DBConnection.getConnection();
    }

    // Phương thức để đăng nhập
    public User login(String userName, String password) throws Exception {
        User user = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String lastName;
        boolean isAdmin;

        try {
            cnn = getConnection();
            String sql = 
                    "SELECT LastName, isAdmin FROM Registration WHERE [UserName] = ? AND [Password] = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs = preStm.executeQuery();

            while (rs.next()) {
                lastName = rs.getString(1);
                isAdmin = rs.getBoolean(2);
                user = new User(userName, password, lastName, isAdmin);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return user;
    }
    // kết thúc login
// <editor-fold defaultstate="collapsed" desc-"Phương thức getUserList">
public List<User> getUserList() throws Exception {
    String userName, password, lastName;
    boolean isAdmin;
    Connection cnn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;
    List<User> userList = new ArrayList();
    
    try {
        cnn = getConnection();
        String sql = "SELECT UserName, Password, LastName, isAdmin FROM Registration";
        preStm = cnn.prepareStatement(sql);
        rs = preStm.executeQuery();
        
        while (rs.next()) {
            userName = rs.getString(1);
            password = rs.getString(2);
            lastName = rs.getString(3);
            isAdmin = rs.getBoolean(4);
            User user = new User(userName, password, lastName, isAdmin);
            userList.add(user);
        } // end while
    } catch (Exception ex) {
        throw ex;
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (cnn != null) {
            cnn.close();
        }
    }

    if (userList.isEmpty()) {
        return null;
    }
    return userList;
}

// end getUserList

public boolean addUser(User user) throws Exception {
    PreparedStatement preStm = null;
    Connection cnn = null;

    try {
        cnn = getConnection();
        String sql = "Insert Registration values(?, ?, ?, ?)";
        preStm = cnn.prepareStatement(sql);
        preStm.setString(1, user.getUserName());
        preStm.setString(2, user.getPassword());
        preStm.setString(3, user.getLastName());
        preStm.setBoolean(4, user.isIsAdmin());
        return preStm.executeUpdate() > 0;
    } catch (Exception ex) {
        throw ex;
    } finally {
        if (preStm != null) {
            preStm.close();
        }
        if (cnn != null) {
            cnn.close();
        }
    }
}

    public boolean updateUser(User user) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "UPDATE Registration SET Password=?, LastName=?, isAdmin=? WHERE UserName=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getPassword());
            preStm.setString(2, user.getLastName());
            preStm.setBoolean(3, user.isIsAdmin());
            preStm.setString(4, user.getUserName());
            return preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

// Phương thức xóa người dùng
    public boolean deleteUser(String userName) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "DELETE FROM Registration WHERE UserName=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            return preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

}
