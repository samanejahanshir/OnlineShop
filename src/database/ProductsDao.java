package database;

import models.Products;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductsDao extends  DataBaseAccess{
    public ProductsDao() throws ClassNotFoundException, SQLException {
        super();
    }
    public int  setProduct(Products products) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("insert into product (name,price,stock,grouping) values('%s',%d,%d,'%s')",products.getName()
            ,products.getPrice(),products.getStock(),products.getGrouping().getTitle());
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i != 0) {
                return getProduct(products);
            }
        } else {
            return -1;
        }
        return -1;
    }
    public int getProduct(Products products) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("select id from product where name='%s' and price=%d",products.getName(),products.getPrice());
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
}
