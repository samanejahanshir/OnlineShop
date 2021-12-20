package models;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  String name;
    private int price;
    private int stock;
    private String productGroup;

  /*  public Products(String name, int price, int stock, String grouping) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.grouping = grouping;

    }*/

   /* public Products(String name, int price) {
        this.name = name;
        this.price = price;
    }*/

    public Products() {

    }

    public int getId() {
        return id;
    }

    public void setId(int idProduct) {
        this.id = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String grouping) {
        this.productGroup = grouping;
    }


    @Override
    public String toString() {
        return "Products{" +
                "idProduct=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", grouping='" + productGroup + '\'' +
                '}';
    }
}
