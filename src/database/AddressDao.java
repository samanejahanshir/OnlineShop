package database;

import models.Address;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class AddressDao extends  DataBaseAccess{
    public AddressDao() throws ClassNotFoundException, SQLException {
        super();
    }
    public int setAddress(Set<Address> addressSet) throws SQLException {
        int i=-1;
        if(getConnection()!=null) {
            for (Address address : addressSet) {
                String sql = String.format("insert into address (id_user,city,postal_code,tag,street) values(%d,'%s','%s','%s','%s')",address.getUserId(),address.getCity(),address.getPostalCode(),address.getTag(),address.getStreet());
                Statement statement = getConnection().createStatement();
                 i=statement.executeUpdate(sql);
            }

        }
        return i;

    }
}
