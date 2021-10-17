package models;

public class Electronics extends Products {
    private  int id;
    private int idProduct;
    private String size;
    private String pow;
    private String possibilities;
    private ElectronicType type;

    public Electronics(String name, int price, int stock, Grouping grouping, String size, String pow, String possibilities, ElectronicType type,int idProduct) {
        super(name, price, stock, grouping);
        this.size = size;
        this.pow = pow;
        this.possibilities = possibilities;
        this.type = type;
        this.idProduct=idProduct;
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

    public ElectronicType getType() {
        return type;
    }

    public void setType(ElectronicType type) {
        this.type = type;
    }
}
