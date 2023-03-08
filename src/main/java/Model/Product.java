package Model;

public class Product{
    public int upc;
    public double price;
    public String name;
    public String description;

    public Product(){

    }

    public Product(int upc, double price, String name, String description){
        this.upc = upc;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}