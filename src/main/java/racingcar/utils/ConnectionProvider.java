package racingcar.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private ConnectionProvider() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
//            DriverManager.registerDriver(new org.h2.Driver());
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
