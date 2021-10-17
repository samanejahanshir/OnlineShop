package models;

public class Products {
    private  int id;
    private  String name;
    private long price;
    private int stock;
    private Grouping grouping;
    private  int idProduct;
    private String type;

    public Products(String name, long price, int stock, Grouping grouping, int idProduct, String type) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.grouping = grouping;
        this.idProduct = idProduct;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Grouping getGrouping() {
        return grouping;
    }

    public void setGrouping(Grouping grouping) {
        this.grouping = grouping;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
