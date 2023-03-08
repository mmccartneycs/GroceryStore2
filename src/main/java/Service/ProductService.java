package Service;

import Model.Product;
import DAO.ProductDAO;

import java.util.List;

public class ProductService{
    private ProductDAO productDAO;

    public ProductService(){
        productDAO = new ProductDAO();
    }

    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }

    public Product getProductByName(String filter){
        return productDAO.getProductByName(filter);
    }

    public List<Product> getProductsByFilters(String filter){
        return productDAO.getProductsByFilters(filter);
    }
}