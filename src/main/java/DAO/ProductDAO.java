package DAO;

import Model.Product;
import Util.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductDAO{

    public List<Product> getAllProducts(){
        Connection connection = ConnectionSingleton.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            String sql = "select * from product;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Product p = new Product(rs.getInt("upc"),
                        rs.getInt("price"),
                        rs.getString("name"),
                        rs.getString("description"));
                products.add(p);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return products;
    }

    public Product getProductByName(String filter){
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "select * from product where name  = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, filter);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Product retProduct = new Product(rs.getInt("upc"),
                        rs.getInt("price"),
                        rs.getString("name"),
                        rs.getString("description"));
                return retProduct;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Product> getProductsByFilters(String filter){
        Connection connection = ConnectionSingleton.getConnection();
        try {
            //TODO: create field for tags. change sql description to tags.
            String sql = "SELECT * FROM product WHERE description contains '?';";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, filter);
            ResultSet rs = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getInt("upc"),
                        rs.getInt("price"),
                        rs.getString("name"),
                        rs.getString("description"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}