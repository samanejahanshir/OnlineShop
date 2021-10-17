package database;

import models.BuyStatus;
import models.Products;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao extends DataBaseAccess {
    public ProductsDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public int setProduct(Products products) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("INSERT INTO `online_shop`.`product` (`name`, `price`, `stock`, `grouping`) VALUES ('%s', %d, %d, '%s');",
                    products.getName(), products.getPrice(), products.getStock(), products.getGrouping());
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i != 0) {
                return i;
            }
        } else {
            return -1;
        }
        return -1;
    }

    public int getProduct(Products products) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("SELECT * FROM online_shop.product WHERE name='%s' AND price=%d;", products.getName(), products.getPrice());
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } else {
            return -1;
        }
        return -1;
    }

    public Products getProductById(int id) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("SELECT * FROM online_shop.product WHERE id=%d", id);
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Products products = new Products(resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5));
                products.setIdProduct(resultSet.getInt(1));
                return products;
            }
        } else {
            return null;
        }
        return null;
    }

    public List<Products> getListProducts() throws SQLException {
        List<Products> productsList = new ArrayList<>();
        if (getConnection() != null) {
            String sql = String.format("SELECT * FROM online_shop.product;");
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do {
                    Products products = new Products(resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5));
                    products.setIdProduct(resultSet.getInt(1));
                    productsList.add(products);
                } while (resultSet.next());
            }

        }
        return productsList;
    }
    public int UpdateProduct(int id,int stock) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("UPDATE online_shop.product SET stock=%d WHERE id=%d;", stock,id);
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i!=0) {
                return i;
            }

        }
        return  -1;
    }
}
