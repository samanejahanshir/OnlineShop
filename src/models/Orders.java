package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private BuyStatus status;
    @ManyToOne
    private User user;
    @OneToOne
    private Products products;

    public Orders() {

    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Orders(BuyStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BuyStatus getStatus() {
        return status;
    }

    public void setStatus(BuyStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
