package models;

import javax.persistence.Entity;

@Entity
public class Shoes extends  Products{
   // private  int id;
   // private int idProduct;
    private String size;
    private String color;
    private String type;

   /* public Shoes(String name, int price, int stock, String grouping, String size, String color, String type) {
        super(name, price, stock, grouping);
        this.size = size;
        this.color = color;
        this.type = type;
        //this.idProduct=idProduct;
    }*/

    public Shoes() {

    }

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

  /*  public int getIdProduct() {
        return idProduct;
    }
*/
   /* public void setIdProduct(int idProduct) {
        this.id = idProduct;
    }*/

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Electronics{" +
                ", id='" +getId() +'\''+
                ", Name='" +getName() +'\''+
                ", price='" +getPrice() +'\''+
                ", stock='" +getStock() +'\''+
               // ", idProduct=" + idProduct +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
