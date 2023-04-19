package racingcar.dao;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CarDao {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert insertActor;

    public CarDao(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("car")
                .usingGeneratedKeyColumns("car_id");
    }

    public int save(CarEntity carEntity) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(carEntity);
        return insertActor.executeAndReturnKey(sqlParameterSource).intValue();
    }

    public List<CarEntity> findByGameId(int gameId) {
        String sql = "select name, position from car where game_id = :gameId";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("gameId", gameId);
        return template.query(sql, parameterSource, rowMapper(gameId));
    }

    public RowMapper<CarEntity> rowMapper(int gameId) {
        return ((rs, rowNum) -> {
            String name = rs.getString("name");
            int position = rs.getInt("position");
            CarEntity carEntity = new CarEntity(name, position, gameId);
            return carEntity;
        });
    }
}
