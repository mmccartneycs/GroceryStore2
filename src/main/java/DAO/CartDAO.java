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

    public void patchCartByUpc(int cart_id, int upc, int quantity){
        Connection con = ConnectionSingleton.getConnection();
        List<Cart> cartList = new ArrayList<>();
        try{
            String sql = "UPDATE cart SET quantity = ? WHERE cart_id = ? AND upc = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, cart_id);
            ps.setInt(3, upc);
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
