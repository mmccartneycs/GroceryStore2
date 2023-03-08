package Service;

import Model.Cart;
import DAO.CartDAO;
import DAO.ProductDAO;

import java.util.List;

public class CartService{
    public CartDAO cartDAO;
    public ProductDAO productDAO;

    public CartService(){
        cartDAO = new CartDAO();
        productDAO = new ProductDAO();
    }

    public List<Cart> getCart(int cart_id){
        return cartDAO.getCart(cart_id);
    }

    public List<Cart> patchCartByUpc(int cart_id, int upc, int quantity){
        cartDAO.patchCartByUpc(cart_id, upc, quantity);
        return cartDAO.getCart(cart_id);
    }
}