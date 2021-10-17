package database;

import models.Orders;
import models.Products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends DataBaseAccess{
    public OrderDao() throws ClassNotFoundException, SQLException {
        super();
    }
    public List<Orders> getListOrders(int id) throws SQLException {
        List<Orders> ordersList=new ArrayList<>();
        if(getConnection()!=null){
            String sql = String.format("SELECT * FROM online_shop.order WHERE idUser=%d AND status='waiting';",id);
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                do{
                    Orders orders= new Orders(resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4));
                  ordersList.add(orders);
                }while (resultSet.next());
            }

        }
        return ordersList;
    }

    public int setOrder(Orders orders) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("INSERT INTO `online_shop`.`order` (`idUser`, `idProduct`, `status`, `date`) VALUES (%d, %d,'%s','%s');",
            orders.getUserId(),orders.getProductId(),orders.getStatus(),orders.getDate().toString());
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

}
