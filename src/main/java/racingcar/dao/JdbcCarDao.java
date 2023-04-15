package racingcar.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import racingcar.dto.CarEntity;

@Component
public class JdbcCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCarDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //TODO: https://stackoverflow.com/questions/3165730/inserting-multiple-rows-using-jdbctemplate 참고하여 변경
    @Override
    public void save(int gameId, List<CarEntity> carEntities) {
        String sql = "INSERT INTO CAR(game_id, name, position, is_win) VALUES (?,?,?,?)";

        for (CarEntity carResultDto : carEntities) {
            jdbcTemplate.update(sql,
                    gameId,
                    carResultDto.getName(),
                    carResultDto.getPosition(),
                    carResultDto.isWin());
        }
    }

    @Override
    public List<CarEntity> findAll() {
        String sql = "SELECT game_id, name, position, is_win FROM CAR";
        return jdbcTemplate.query(sql, carEntityRowMapper());
    }

    private RowMapper<CarEntity> carEntityRowMapper() {
        return (rs, rowNum) -> {
            final int gameId = rs.getInt("game_id");
            final String name = rs.getString("name");
            final int position = rs.getInt("position");
            final boolean isWin = rs.getBoolean("is_win");

            return new CarEntity(gameId, name, position, isWin);
        };
    }

}
