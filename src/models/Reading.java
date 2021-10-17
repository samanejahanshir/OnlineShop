package models;

public class Reading {
    private  int id;
    private  int pages;
    private  String size;
    private String material;
    private  ReadingType type;

    public Reading(int pages, String size, String material, ReadingType type) {
        this.pages = pages;
        this.size = size;
        this.material = material;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
