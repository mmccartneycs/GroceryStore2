package Controller;

import Model.Cart;
import Model.Product;
import Model.User;
import Service.CartService;
import Service.ProductService;
import Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;


import java.util.List;

public class GroceryController{
    CartService cartService;
    ProductService productService;
    UserService userService;
    public GroceryController(){
        this.cartService = new CartService();
        this.productService = new ProductService();
        this.userService = new UserService();
    }

    public Javalin startAPI(){
        Javalin app = Javalin.create();

        app.post("/register", this::postUserHandler);
        app.post("/login", this::postLoginUserHandler);
        //app.patch("/member/{member_id}", this::patchUserInfoHandler);
        app.patch("/cart/{quantity}", this::patchCartHandler);
        app.get("/cart/{cart_id}", this::getCartHandler);
        app.get("/checkout/{member_id}", this::getCheckoutMemberHandler);
        //app.post("/cart/checkout", this::postCheckoutHandler);
        app.get("/product", this::getProductsHandler);
        app.get("/product/{item}", this::getItemByNameHandler);
        app.get("/product/{search}", this::getSearchHandler);
        app.get("/product/{filters}", this::getFiltersHandler);
        return app;
    }

    private void postUserHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(ctx.body(), User.class);
        User newUser = userService.addUser(user);
        if(newUser != null){
            ctx.json(newUser);
        }else{
            ctx.status(400);
        }
    }

    private void postLoginUserHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(ctx.body(), User.class);
        User login = userService.verifyUser(user);
        if(login != null){
            ctx.json(login);
        }else{
            ctx.status(401);
        }
    }

    /*private void patchUserInfoHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper
    }*/

    private void patchCartHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Cart cart = om.readValue(ctx.body(), Cart.class);
        int aId = cart.getAccount_id();
        int cId = cart.getCart_id();
        int upc = cart.getUpc();
        int q = cart.getQuantity();
        Cart patchedCart = CartService.patchCartByUpc(aId, cId, upc, q);
        if(patchedCart != null)
            ctx.json(patchedCart);
        else
            ctx.json("Empty cart");
    }

    private void getCartHandler(Context ctx) throws JsonProcessingException{
        String user_id = ctx.pathParam("cart_id");
        int id = Integer.parseInt(user_id);
        ctx.json(cartService.getCart(id));
    }

    private void getCheckoutMemberHandler(Context ctx) throws JsonProcessingException{
        String user_id = ctx.pathParam("member_id");
        int id = Integer.parseInt(user_id);
        ctx.json(userService.getCredentials(id));
    }

    /*private void postCheckoutHandler(Context ctx) throws JsonProcessingException{

    }*/

    private List<Product> getProductsHandler(Context ctx) throws JsonProcessingException{
        ctx.json(productService.getAllProducts());
    }

    private void getItemByNameHandler(Context ctx) throws JsonProcessingException{
        String item_input = ctx.pathParam("item");
        Product product = productService.getProductByName(item_input);
        if(product != null){
            ctx.json(product);
        }
    }
    private void getSearchHandler(Context ctx) throws JsonProcessingException{
        String search_input = ctx.pathParam("search");
        Product product = productService.getProductByName(search_input);
        if(product != null){
            ctx.json(product);
        }
    }
    private void getFiltersHandler(Context ctx) throws JsonProcessingException{
        String filters = ctx.pathParam("filters");
        ctx.json(productService.getProductsByFilters(filters));
    }
}