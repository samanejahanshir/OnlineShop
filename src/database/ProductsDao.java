package database;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
       /* if (getConnection() != null) {
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
        return -1;*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id=(int)session.save(products);
        transaction.commit();
        session.close();
        return id;
    }

    public Products getProduct(Products products) throws SQLException {
      /*  if (getConnection() != null) {
            String sql = String.format("SELECT * FROM online_shop.product WHERE name='%s' AND price=%d;", products.getName(), products.getPrice());
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } else {
            return -1;
        }
        return -1;*/
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Products> query = session.createQuery("from Products p where p.name=:name and p.price=:price", Products.class);
        query.setParameter("name",products.getName());
        query.setParameter("price",products.getPrice());
        List<Products> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.get(0);
    }

    public Products getProductById(int id) throws SQLException {
       /* if (getConnection() != null) {
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
        return null;*/
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Products> query = session.createQuery("from Products p where p.id=:id", Products.class);
        query.setParameter("id",id);
        List<Products> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.get(0);

    }

    public List<Products> getListProducts() throws SQLException {
       /* List<Products> productsList = new ArrayList<>();
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
        return productsList;*/
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Products> query = session.createQuery("from Products", Products.class);
        List<Products> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    public int UpdateProduct(int id, int stock) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("UPDATE online_shop.product SET stock=%d WHERE id=%d;", stock, id);
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i != 0) {
                return i;
            }

        }
        return -1;
    }

    public String getDetailProduct(String type, int id) throws SQLException {
        if (getConnection() != null) {
            String sql = "";
            boolean electronic=false,reading=false,shoes=false;
            if (type.equals(Grouping.ELECTRONIC.getTitle())) {
                electronic=true;
                sql = String.format("SELECT * FROM online_shop.product INNER JOIN online_shop.electronic WHERE product.id=electronic.idProduct AND electronic.idProduct=%d;",id);
            } else if (type.equals(Grouping.READING.getTitle())) {
                reading=true;
                sql = String.format("SELECT * FROM online_shop.product INNER JOIN online_shop.reading WHERE product.id=reading.id_products AND reading.id_products=%d;",id);
            } else if (type.equals(Grouping.SHOES.getTitle())) {
                shoes=true;
                sql = String.format("SELECT * FROM online_shop.product INNER JOIN online_shop.shoes WHERE product.id=shoes.id_Product AND shoes.id_Product=%d;",id);
            }
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                if(electronic) {
                    return "Id : " + resultSet.getInt(1) + ", Name : " + resultSet.getString(2)
                            + ", Price : " + resultSet.getInt(3) + ", Stock : " + resultSet.getInt(4)
                            + ", Size : " +resultSet.getString(7)+", Pow : "+resultSet.getString(8)+", Possibilities : "+resultSet.getString(9);
                }
                else if(reading){
                    return "Id : " + resultSet.getInt(1) + ", Name : " + resultSet.getString(2)
                            + ", Price : " + resultSet.getInt(3) + ", Stock : " + resultSet.getInt(4)
                            + ", Pages : " +resultSet.getInt(7)+", Size : "+resultSet.getString(8)+", Material : "+resultSet.getString(9);


                }else  if(shoes){
                    return "Id : " + resultSet.getInt(1) + ", Name : " + resultSet.getString(2)
                            + ", Price : " + resultSet.getInt(3) + ", Stock : " + resultSet.getInt(4)
                            + ", Size : " +resultSet.getString(7)+", Color : "+resultSet.getString(8);
                }

            }
        }
        return null;

    }
    public  boolean removeProduct(Products products) throws SQLException {
      /*  if (getConnection() != null) {
            String sql="";
            int i=0;
            if(products.getGrouping().equals(Grouping.ELECTRONIC.getTitle())){
                sql = String.format("DELETE FROM online_shop.electronic WHERE idProduct=%d",products.getIdProduct());
                Statement statement = getConnection().createStatement();
                i = statement.executeUpdate(sql);

            }
            else if(products.getGrouping().equals(Grouping.SHOES.getTitle())){
                sql = String.format("DELETE FROM online_shop.shoes WHERE id_Product=%d",products.getIdProduct());
                Statement statement = getConnection().createStatement();
                i = statement.executeUpdate(sql);
            }
            else  if(products.getGrouping().equals(Grouping.READING.getTitle())){
                sql = String.format("DELETE FROM online_shop.reading WHERE id_products=%d",products.getIdProduct());
                Statement statement = getConnection().createStatement();
                i = statement.executeUpdate(sql);

            }
            if(i!=0){
                sql = String.format("DELETE FROM online_shop.product WHERE id=%d",products.getIdProduct());
                Statement statement = getConnection().createStatement();
                i = statement.executeUpdate(sql);
                if (i != 0) {
                    return true;
                }

            }

        }
        return  false;*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(products);
        transaction.commit();
        session.close();
        return true;

    }

}
