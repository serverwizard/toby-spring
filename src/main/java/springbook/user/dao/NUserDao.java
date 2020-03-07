package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {

    @Override
    public Connection getConnection() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/springbook";
        final String user = "spring";
        final String password = "book";
        return DriverManager.getConnection(url, user, password);
    }

}
