package racingcar.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import racingcar.dto.CarDto;

@Repository
public class JdbcCarDao implements CarDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertCar(List<CarDto> carDtos, long gameId) {
        String sql = "INSERT INTO car(g_id, name, position) VALUES (?,?,?)";
        List<Object[]> carsInfo = getCarsInfo(gameId, carDtos);
        jdbcTemplate.batchUpdate(sql, carsInfo);
    }

    private List<Object[]> getCarsInfo(long gameId, List<CarDto> carDtos) {
        return carDtos.stream()
                .map(carDto -> {
                    Object[] objects = new Object[3];
                    objects[0] = gameId;
                    objects[1] = carDto.getName();
                    objects[2] = carDto.getPosition();
                    return objects;
                })
                .collect(Collectors.toUnmodifiableList());
    }
}
