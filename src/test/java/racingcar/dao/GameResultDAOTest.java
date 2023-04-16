package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameResultDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan
@JdbcTest
class GameResultDAOTest {
    @Autowired
    private GameResultDAO gameResultDAO;

    @DisplayName("게임 결과를 저장할 수 있다.")
    @Transactional
    @Test
    void findAllGameResultTest() {
        //given
        List<GameResultDto> results = List.of(
                new GameResultDto(5, "dochi"),
                new GameResultDto(10, "zuny")
        );

        //when
        for (GameResultDto result : results) {
            gameResultDAO.save(result);
        }
        List<GameResultDto> findResults = gameResultDAO.findAll();

        //then
        for (int i = 0; i < findResults.size(); i++) {
            assertThat(findResults.get(i).getWinners())
                    .isEqualTo(results.get(i).getWinners());

            assertThat(findResults.get(i).getTrialCount())
                    .isEqualTo(results.get(i).getTrialCount());
        }
    }
}
