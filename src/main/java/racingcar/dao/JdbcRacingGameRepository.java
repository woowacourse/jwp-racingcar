package racingcar.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.entity.Game;
import racingcar.entity.RacingGameEntity;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class JdbcRacingGameRepository implements RacingGameRepository {

    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public JdbcRacingGameRepository(final GameDao gameDao, final PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    public static JdbcRacingGameRepository generateDefaultJdbcRacingGameRepository() {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(generateDataSource());
        return new JdbcRacingGameRepository(new JdbcGameDao(jdbcTemplate), new JdbcPlayerDao(jdbcTemplate));
    }

    public static DataSource generateDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/racingcar");
        dataSource.setUsername("root");
        dataSource.setPassword("xxxx");
        return dataSource;
    }

    @Override
    public Long save(final RacingGameEntity racingGameEntity) {
        final Long gameId = gameDao.save(racingGameEntity.getGame());
        playerDao.save(gameId, racingGameEntity.getPlayer());
        return gameId;
    }

    @Override
    public List<RacingGameEntity> findAll() {
        final List<Game> games = gameDao.findAll();
        return games.stream()
                .map(game -> new RacingGameEntity(game, playerDao.findById(game.getId())))
                .collect(Collectors.toList());
    }
}
