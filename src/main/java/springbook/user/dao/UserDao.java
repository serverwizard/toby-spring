package springbook.user.dao;

import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        final Connection con = dataSource.getConnection();

        final PreparedStatement ps = con.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public User get(String id) throws SQLException {
        final Connection con = dataSource.getConnection();

        final PreparedStatement ps = con.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        final ResultSet rs = ps.executeQuery();
        rs.next();

        final User user = User.of(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        con.close();

        return user;
    }

}
