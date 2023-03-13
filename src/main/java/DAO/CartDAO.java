package DAO;

import Model.Cart;
import Util.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO{

    public List<Cart> getCart(int cart_id){
        Connection con = ConnectionSingleton.getConnection();
        List<Cart> cartList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM cart WHERE cart_id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cart_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cart cart = new Cart(rs.getInt("account_id"),
                        rs.getInt("cart_id"),
                        rs.getInt("upc"),
                        rs.getInt("quantity"));
                cartList.add(cart);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return cartList;
    }

    public void patchCartByUpc(Cart cart){
        Connection con = ConnectionSingleton.getConnection();
        List<Cart> cartList = new ArrayList<>();
        try{
            String sql = "UPDATE cart SET quantity = ? WHERE cart_id = ? AND upc = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cart.getQuantity());
            ps.setInt(2, cart.getCart_id());
            ps.setInt(3, cart.getUpc());
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteItemByUpc(int cart_id, int upc){
        Connection con = ConnectionSingleton.getConnection();
        List<Cart> cartList = new ArrayList<>();
        try{
            String sql = "DELETE FROM cart WHERE cart_id = ? AND upc = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cart_id);
            ps.setInt(2, upc);
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void postItem(Cart cart){
        Connection con = ConnectionSingleton.getConnection();
        try{
            String sql = "INSERT INTO cart (account_id, upc, cart_id, quantity) VALUES (?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cart.getAccount_id());
            ps.setInt(2, cart.getUpc());
            ps.setInt(3, cart.getCart_id());
            ps.setInt(4, cart.getQuantity());
            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
