package racingcar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.controller.dto.PlaysRequest;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.entity.Game;

import java.util.List;

@SpringBootTest
@Transactional //afterEach로 초기화 안 시켜도 테스트 실행후 마지막에 rollback 해주는 annotation (DB 반영 x)
class RacingCarServiceTest {

    @Autowired
    private RacingCarService racingCarService;
    @Autowired
    private GameDao gameDao;
    @Autowired
    private PlayerDao playerDao;

    @DisplayName("play를 하면 게임결과를 DB에 저장된다.")
    @Test
    void play() {
        //given
        PlaysRequest playsRequest = new PlaysRequest("judy, judy2, judy3", 10);

        //when
        racingCarService.play(playsRequest);

        List<Game> games = gameDao.selectAll();
        Game game = games.get(0);

        //then
        Assertions.assertEquals(1, gameDao.selectAll().size());
        Assertions.assertEquals(3, playerDao.selectAllByGameId(game.getId()).size());
    }
}
