package com.pplenty.studytoby.chapter01.step01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by yusik on 2020/03/09.
 */
@DisplayName("오브젝트와 의존관계")
public class MainTest {

    @BeforeEach
    void setUp() throws SQLException {

        Connection con = DriverManager.getConnection(
                "jdbc:mariadb://localhost:63306/toby", "jason", "qwe123");

        PreparedStatement ps = con.prepareStatement("truncate toby.users");

        ps.executeUpdate();

    }

    @DisplayName("UserDao 의 변화")
    @Test
    void add() throws SQLException {

        // given
        ConnectionMaker connectionMaker = new DConnectionMaker();
//        ConnectionMaker connectionMaker = new SimpleConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("koh");
        user.setName("yusik");
        user.setPassword("1234");

        // when
        userDao.add(user);
        User result = userDao.get("koh");

        // then
        assertThat(result.getId()).isEqualTo(user.getId());
        System.out.println(result);

    }

}
