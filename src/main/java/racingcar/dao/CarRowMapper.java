package racingcar.dao;

import org.springframework.jdbc.core.RowMapper;
import racingcar.domain.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Car car = new Car(resultSet.getString("name"), resultSet.getInt("position"));
        return car;
    }
}
