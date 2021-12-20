package database;

import models.Shoes;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ShoesDao extends  DataBaseAccess{
    public ShoesDao() throws ClassNotFoundException, SQLException {
        super();
    }
    public int  setShoes(Shoes shoes) throws SQLException {
       /* if (getConnection() != null) {
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
        return -1;*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id=(int)session.save(shoes);
        transaction.commit();
        session.close();
        return id;
    }

    public Shoes getShoes(Shoes shoes) throws SQLException {
       /* if (getConnection() != null) {
            String sql = String.format("select id from shoes where id_Product=%d",shoes.getIdProduct());
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
        Query<Shoes> query = session.createQuery("from Shoes shose where shose.id=:id", Shoes.class);
        query.setParameter("id",shoes.getId());
        List<Shoes> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.get(0);
    }
}
