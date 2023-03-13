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

    public List<Cart> patchCartByUpc(Cart cart){
        if(cart.getQuantity()<=0){
            cartDAO.deleteItemByUpc(cart.getCart_id(), cart.getUpc());
        }
        else {
            cartDAO.patchCartByUpc(cart);
        }
        return cartDAO.getCart(cart.getCart_id());
    }

    public void postItem(Cart cart){
        if(productDAO.getProductByUpc(cart.getUpc()) != null){
            cartDAO.postItem(cart);
        }
        //return getCart(cart.getCart_id());
    }
}