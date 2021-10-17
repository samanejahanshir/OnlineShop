package models;

public class Electronics {
    private  int id;
    private String size;
    private String pow;
    private String possibilities;
    private ElectronicType type;

    public Electronics(String size, String pow, String possibilities, ElectronicType type) {
        this.size = size;
        this.pow = pow;
        this.possibilities = possibilities;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
