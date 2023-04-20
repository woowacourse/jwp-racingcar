package racingcar.dao.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcInsert {

    private JdbcInsert() {
    }

    public static int getIdAfterInsert(JdbcTemplate jdbcTemplate, String sqlForInsert, String... sqlParameters) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlForInsert, Statement.RETURN_GENERATED_KEYS);
            setSqlParameter(preparedStatement, sqlParameters);
            return preparedStatement;
        }, keyHolder);
        return (int) keyHolder.getKey();
    }

    private static void setSqlParameter(PreparedStatement preparedStatement, String... sqlParameters) throws SQLException {
        for (int parameterIndex = 1; parameterIndex <= sqlParameters.length; parameterIndex++) {
            preparedStatement.setString(parameterIndex, sqlParameters[parameterIndex - 1]);
        }
    }

}
