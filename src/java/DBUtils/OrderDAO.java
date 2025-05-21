/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author myapa
 */
public class OrderDAO {
    public Connection getConnection() {
        return DBConnection.getConnection();
    }
    
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
    public boolean addOrder(Order order) throws Exception {
    PreparedStatement preStm = null;
    Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "INSERT INTO Orders (UserName , id , quantity ) VALUES (?, ?, ?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, order.getUserName());
            preStm.setInt(2, order.getProductId());
            preStm.setDouble(3, order.getQuantity());
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
