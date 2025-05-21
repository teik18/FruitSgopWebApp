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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author myapa
 */
public class ProductDAO {
    
    public Connection getConnection() {
        return DBConnection.getConnection();
    }
    
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM product";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                productList.add(p);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    public boolean insertProduct(String name, double price) {
        String sql = "INSERT INTO Product (name, price) VALUES (?,?)";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, name);
            ps.setDouble(2, price);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM Product WHERE id = ?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateProduct(Product p) {
        String sql = "UPDATE Product SET name = ?, price = ? WHERE id = ?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getId());
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Product getProductById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Product> searchByName(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE name LIKE ?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Product p = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
                list.add(p);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

