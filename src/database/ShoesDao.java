package database;

import models.Electronics;
import models.Shoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShoesDao extends  DataBaseAccess{
    public ShoesDao() throws ClassNotFoundException, SQLException {
        super();
    }
    public int  setShoes(Shoes shoes) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("insert into shoes (size,color,type,id_Product) values('%s','%s','%s',%d)",shoes.getSize(),
                    shoes.getColor(),shoes.getType(),shoes.getIdProduct());
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i != 0) {
                return getShoes(shoes);
            }
        } else {
            return -1;
        }
        return -1;
    }

    public int getShoes(Shoes shoes) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("select id from shoes where id_Product=%d",shoes.getIdProduct());
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
