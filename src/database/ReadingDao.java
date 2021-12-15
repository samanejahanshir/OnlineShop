package database;

import models.Reading;
import models.Shoes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadingDao extends  DataBaseAccess{
    public ReadingDao() throws ClassNotFoundException, SQLException {
        super();
    }
    public int  setReading(Reading reading) throws SQLException {
       /* if (getConnection() != null) {
            String sql = String.format("insert into reading (pages,size,material,type,id_products) values(%d,'%s','%s','%s',%d)",reading.getPages(),reading.getSize(),
                    reading.getMaterial(),reading.getType(),reading.getIdProduct());
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i != 0) {
                return getReading(reading);
            }
        } else {
            return -1;
        }
        return -1;*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id=(int)session.save(reading);
        transaction.commit();
        session.close();
        return id;
    }

    public int getReading(Reading reading) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("select id from reading where id_products=%d",reading.getIdProduct());
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
