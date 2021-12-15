package database;

import models.BuyStatus;
import models.Orders;
import models.Products;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends DataBaseAccess {
    public OrderDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public List<Orders> getListOrders(int id) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        if (getConnection() != null) {
            String sql = String.format("SELECT * FROM online_shop.order WHERE idUser=%d AND status='waiting';", id);
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do {
                    Orders orders = new Orders(resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4));
                    orders.setId(resultSet.getInt(1));
                    ordersList.add(orders);
                } while (resultSet.next());
            }

        }
        return ordersList;
    }

    public int setOrder(Orders orders) throws SQLException {
       /* if (getConnection() != null) {
            String sql = String.format("INSERT INTO `online_shop`.`order` (`idUser`, `idProduct`, `status`, `date`) VALUES (%d, %d,'%s','%s');",
                    orders.getUserId(), orders.getProductId(), orders.getStatus(), orders.getDate().toString());
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
        int id=(int)session.save(orders);
        transaction.commit();
        session.close();
        return id;
    }
public Orders getOrderById(int id) throws SQLException {
   /* if (getConnection() != null) {
        String sql = String.format("SELECT * FROM online_shop.order WHERE idOrder=%d;", id);
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

    }
    return  -1;*/
    Session session=DataBaseAccess.getSessionFactory().openSession();
    Transaction transaction=session.beginTransaction();
    Query<Orders> query = session.createQuery("from Orders order where order.id=:id", Orders.class);
    query.setParameter("id",id);
    List<Orders> resultList = query.getResultList();
    transaction.commit();
    session.close();
    return resultList.get(0);
}
    public int deleteOrder(int id) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("DELETE  FROM online_shop.order WHERE idOrder=%d;", id);
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i!=0) {
                return i;
            }

        }
        return  -1;

    }
    public int UpdateOrders(int id) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("UPDATE online_shop.order SET status='%s' WHERE idUser=%d;", BuyStatus.END.getTitle(),id);
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i!=0) {
                return i;
            }

        }
        return  -1;
    }


}
