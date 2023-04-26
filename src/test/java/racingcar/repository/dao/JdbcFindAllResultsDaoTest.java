package racingcar.repository.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import racingcar.repository.dto.ResultDto;
import racingcar.repository.entity.CarEntity;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.PlayerEntity;
import racingcar.repository.entity.WinnerEntity;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JdbcFindAllResultsDaoTest {

    @Autowired
    private DataSource dataSource;

    private CarDao carDao;
    private GameDao gameDao;
    private PlayerDao playerDao;
    private WinnerDao winnerDao;
    private FindAllResultsDao findAllResultsDao;

    @BeforeEach
    void setUp() {
        this.carDao = new JdbcCarDao(dataSource);
        this.gameDao = new JdbcGameDao(dataSource);
        this.playerDao = new JdbcPlayerDao(dataSource);
        this.winnerDao = new JdbcWinnerDao(dataSource);
        this.findAllResultsDao = new JdbcFindAllResultsDao(dataSource);
    }

    @Test
    void findAll_메서드로_모든_게임_결과를_가져온다() {
        // given
        final PlayerEntity playerEntity1 = new PlayerEntity("player1");
        final PlayerEntity playerEntity2 = new PlayerEntity("player2");
        long player1Id = playerDao.save(playerEntity1);
        long player2Id = playerDao.save(playerEntity2);

        final GameEntity gameEntity = new GameEntity(1L, 5, LocalDateTime.now());
        final long gameId = gameDao.save(gameEntity);

        final CarEntity carEntity1 = new CarEntity(gameId, player1Id, 5);
        final CarEntity carEntity2 = new CarEntity(gameId, player2Id, 5);
        carDao.save(carEntity1);
        carDao.save(carEntity2);

        WinnerEntity winner1Entity = new WinnerEntity(gameId, player1Id);
        WinnerEntity winner2Entity = new WinnerEntity(gameId, player2Id);
        winnerDao.save(winner1Entity);
        winnerDao.save(winner2Entity);

        // when
        List<ResultDto> resultDtos = findAllResultsDao.findAll();

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(resultDtos.size()).isEqualTo(2);
            softAssertions.assertThat(resultDtos.get(0).getPosition()).isEqualTo(5);
            softAssertions.assertThat(resultDtos.get(1).getPosition()).isEqualTo(5);
        });
    }
}
