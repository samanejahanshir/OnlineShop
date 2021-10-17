package models;

public class Electronics extends Products {
    private  int id;
    private int idProduct;
    private String size;
    private String pow;
    private String possibilities;
    private String type;

    public Electronics(String name, int price, int stock, String grouping, String size, String pow, String possibilities, String type,int idProduct) {
        super(name, price, stock, grouping);
        this.size = size;
        this.pow = pow;
        this.possibilities = possibilities;
        this.type = type;
        this.idProduct=idProduct;
    }

    public int getIdProduct() {
        return idProduct;
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

    public String getPow() {
        return pow;
    }

    public void setPow(String pow) {
        this.pow = pow;
    }

    public String getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(String possibilities) {
        this.possibilities = possibilities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
