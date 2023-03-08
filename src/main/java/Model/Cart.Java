package Model;

public class Cart {
    public int account_id;
    public int cart_id;
    public int upc;
    public int quantity;

    public Cart(int account_id, int cart_id, int upc, int quantity){
        this.account_id = account_id;
        this.cart_id = cart_id;
        this.upc = upc;
        this.quantity = quantity;
    }
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
