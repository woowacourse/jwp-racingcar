package racingcar.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameFindDto;
import racingcar.dto.PlayerSaveDto;
import racingcar.dto.RacingGameFindDto;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class JdbcRacingGameRepository implements RacingGameRepository {

    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public JdbcRacingGameRepository(GameDao gameDao, PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    public static JdbcRacingGameRepository generateDefaultJdbcRacingGameRepository() {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(generateDataSource());
        return new JdbcRacingGameRepository(new JdbcGameDao(jdbcTemplate), new JdbcPlayerDao(jdbcTemplate));
    }

    public static DataSource generateDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("sa");
        return dataSource;
    }

    @Override
    public Long save(final int trialCount, final List<PlayerSaveDto> playerSaveDtos) {
        final Long gameId = gameDao.save(trialCount);
        playerDao.save(gameId, playerSaveDtos);
        return gameId;
    }

    @Override
    public List<RacingGameFindDto> findAll() {
        final List<GameFindDto> gameFindDtos = gameDao.findAll();
        return gameFindDtos.stream()
                .map(gameFindDto -> new RacingGameFindDto(gameFindDto, playerDao.findById(gameFindDto.getId())))
                .collect(Collectors.toList());
    }
}
