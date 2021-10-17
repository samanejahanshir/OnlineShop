package database;

import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao extends DataBaseAccess {
    public UserDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public int getUser(User user) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("select idUser from user where name='%s' and email='%s'", user.getName(), user.getEmail());
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

    public int setUser(User user) throws SQLException {
        if (getConnection() != null) {
            String sql = String.format("insert into user (name,password,email) values('%s','%s','%s')", user.getName(), user.getPassword(), user.getEmail());
            Statement statement = getConnection().createStatement();
            int i = statement.executeUpdate(sql);
            if (i != 0) {
                return getUser(user);
            }
        } else {
            return -1;
        }
        return -1;
    }
}
