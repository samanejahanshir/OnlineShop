package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private int userId;
    @Transient
    private int productId;
    private LocalDateTime date;
    private String status;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Orders() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Orders(int userId, int productId, String status) {
        this.userId = userId;
        this.productId = productId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
