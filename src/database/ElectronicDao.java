package database;

import models.Electronics;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ElectronicDao extends DataBaseAccess {
    public ElectronicDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public int setElectronic(Electronics electronics) throws SQLException {
       /* if (getConnection() != null) {
            String sql = String.format("INSERT INTO `online_shop`.`electronic` (`size`, `pow`, `possibilities`, `type`, `idProduct`) VALUES ('%s', '%s', '%s', '%s', %d);\n",electronics.getSize(),
                    electronics.getPow(),electronics.getPossibilities(),electronics.getType(),electronics.getIdProduct());
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i != 0) {
                return getElectronic(electronics);
            }
        } else {
            return -1;
        }
        return -1;*/

        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(electronics);
        transaction.commit();
        session.close();
        return id;
    }

    public Electronics getElectronic(Electronics electronics) throws SQLException {
       /* if (getConnection() != null) {
            String sql = String.format("select id from electronic where idProduct=%d", electronics.getIdProduct());
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
        Query<Electronics> query = session.createQuery("from Electronics e where e.id=:id", Electronics.class);
        query.setParameter("id",electronics.getId());
        List<Electronics> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.get(0);
    }
}
