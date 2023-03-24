package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    // Refactoring -> cmd + opt + m
    // 1. 중복을 피해라
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 데이터 어딨어? mysql
        // mysql 클래스 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Connection 맺고
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/jeju", "root", "");
        return connection;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        // 쿼리 만들고
        PreparedStatement preparedStatement = connection.prepareStatement
                ("select  id, name, password from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        // 쿼리 실행하고
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        // 결과를 사용자 정보에 매핑
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        // 자원을 해지
        connection.close();
        preparedStatement.close();
        resultSet.close();
        // 결과를 리턴
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        // 데이터 어딨어? mysql
        // mysql 클래스 로딩
        Connection connection = getConnection();
        // 쿼리 만들고
        PreparedStatement preparedStatement = connection.prepareStatement
                ("insert into userinfo (name, password) values (?, ?)"
                        , Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        // 쿼리 실행하고
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        // 결과를 사용자 정보에 매핑
        user.setId(resultSet.getLong(1));

        // 자원을 해지
        connection.close();
        preparedStatement.close();
        resultSet.close();
    }
}
