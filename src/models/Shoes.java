package models;

public class Shoes extends  Products{
    private  int id;
    private int idProduct;
    private String size;
    private String color;
    private ShoesType type;

    public Shoes(String name, int price, int stock, Grouping grouping, String size, String color, ShoesType type,int idProduct) {
        super(name, price, stock, grouping);
        this.size = size;
        this.color = color;
        this.type = type;
        this.idProduct=idProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return id;
    }

    public void setIdProduct(int idProduct) {
        this.id = idProduct;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ShoesType getType() {
        return type;
    }

    public void setType(ShoesType type) {
        this.type = type;
    }
}
