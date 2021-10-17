package service;

import database.AddressDao;
import database.ProductsDao;
import database.UserDao;
import models.Orders;
import models.Products;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    public List<Products> products = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    public List<Orders> orders = new ArrayList<>();
    public static UserDao userDao;
    public static AddressDao addressDao;
    public static ProductsDao productsDao;

    public Shop() throws SQLException, ClassNotFoundException {
        userDao=new UserDao();
        addressDao=new AddressDao();
        productsDao=new ProductsDao();
    }
}
