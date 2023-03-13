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
import io.javalin.http.Context;


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
        app.patch("/cart", this::patchCartHandler);
        app.get("/cart/{cart_id}", this::getCartHandler);
        app.post("/cart", this::postCartHandler);
        //app.get("/checkout/{cart_id}", this::getCheckoutMemberHandler);
        //app.post("/cart/checkout", this::postCheckoutHandler);
        app.get("/product", this::getProductsHandler);
        app.get("/product/{search}", this::getSearchHandler);
        app.get("/product/tag/{filters}", this::getFiltersHandler);
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
        User login = userService.validateUser(user);
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
        List<Cart> patchedCart = cartService.patchCartByUpc(cart);
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

    private void postCartHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Cart cart = om.readValue(ctx.body(), Cart.class);
        cartService.postItem(cart);
        ctx.json(cartService.getCart(cart.getCart_id()));
    }

    /*private void getCheckoutMemberHandler(Context ctx) throws JsonProcessingException{
        String cart_id = ctx.pathParam("member_id");
        int id = Integer.parseInt(cart_id);
        ctx.json(userService.getCredentials(id));
    }

    private void postCheckoutHandler(Context ctx) throws JsonProcessingException{

    }*/

    private void getProductsHandler(Context ctx) throws JsonProcessingException{
        ctx.json(productService.getAllProducts());
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