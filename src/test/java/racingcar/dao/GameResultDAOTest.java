package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayerResultDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan
@JdbcTest
class GameResultDAOTest {
    @Autowired
    private GameResultDAO gameResultDAO;

    @Autowired
    private PlayerResultDAO playerResultDAO;

    @DisplayName("최종 게임 결과를 저장할 수 있다.")
    @Transactional
    @Test
    void findAllGameResultTest() {
        //given
        List<GameResultDto> results = List.of(
                GameResultDto.from(5),
                GameResultDto.from(10)
        );

        //when
        for (GameResultDto result : results) {
            gameResultDAO.save(result);
        }
        List<GameResultEntity> findResults = gameResultDAO.findAll();

        //then
        for (int i = 0; i < findResults.size(); i++) {
            assertThat(findResults.get(i).getTrialCount())
                    .isEqualTo(results.get(i).getTrialCount());
        }
    }

    @DisplayName("게임 결과 및 플레이어들의 결과를 모두 가져올 수 있다.")
    @Transactional
    @Test
    void findAllGameAndPlayerResultsTest() {
        //given
        GameResultDto gameResult = GameResultDto.from(5);
        int savedId = gameResultDAO.save(gameResult);
        PlayerResultDto playerResult = PlayerResultDto.of(List.of(CarDto.of("쥬니", 1)), savedId);
        playerResultDAO.saveAll(playerResult);

        //when
        GameAndPlayerResultEntity gameAndPlayerResult = gameResultDAO.findAllWithPlayerResults().get(0);

        //then
        assertThat(gameAndPlayerResult.getGameId())
                .isEqualTo(savedId);
        assertThat(gameAndPlayerResult.getTrialCount())
                .isEqualTo(5);
        assertThat(gameAndPlayerResult.getName())
                .isEqualTo("쥬니");
        assertThat(gameAndPlayerResult.getPosition())
                .isEqualTo(1);
    }
}
