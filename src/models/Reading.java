package models;

public class Reading extends Products{
    private  int id;
    private int idProduct;
    private  int pages;
    private  String size;
    private String material;
    private  ReadingType type;


    public Reading(String name, int price, int stock, Grouping grouping, int pages, String size, String material, ReadingType type1,int idProduct) {
        super(name, price, stock, grouping);
        this.pages = pages;
        this.size = size;
        this.material = material;
        this.type = type1;
        this.idProduct=idProduct;
    }

    public int getIdProduct() {
        return id;
    }

    public void setIdProduct(int idProduct) {
        this.id = idProduct;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public ReadingType getType() {
        return type;
    }

    public void setType(ReadingType type) {
        this.type = type;
    }
}
