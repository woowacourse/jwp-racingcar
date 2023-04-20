package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.dto.request.GameSaveDto;
import racingcar.dto.request.PlayerResultSaveDto;
import racingcar.dto.response.PlayerResultDto;

import java.util.List;

@JdbcTest
@Import({PlayerResultDao.class, GameDao.class})
class PlayerResultDaoTest {

    @Autowired
    PlayerResultDao playerResultDao;
    @Autowired
    GameDao gameDao;

    @Test
    @DisplayName("게임 결과 저장 시 해당 게임의 참가자가 모두 저장된다.")
    @Transactional
    void insertPlayerResultTest() {
        //given
        GameSaveDto game = new GameSaveDto("leo", 5);
        Long gameId = gameDao.createGame(game);

        Car leoCar = new Car("leo");
        PlayerResultSaveDto playerResultSaveDto = new PlayerResultSaveDto(gameId, leoCar);

        List<PlayerResultSaveDto> playerResults = List.of(playerResultSaveDto);
        playerResultDao.savePlayerResult(playerResults);
        //when
        List<PlayerResultDto> playerResultsByGameId = playerResultDao.findPlayerResultsByGameId(gameId);

        //then
        Assertions.assertThat(playerResultsByGameId.size()).isEqualTo(1);
    }
}
