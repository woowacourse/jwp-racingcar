package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayerResultDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan
@JdbcTest
class PlayerResultDAOTest {
    @Autowired
    private GameResultDAO gameResultDAO;
    @Autowired
    private PlayerResultDAO playerResultDAO;

    @DisplayName("플레이어들의 게임 결과를 저장할 수 있다.")
    @Transactional
    @Test
    void savePlayerResultTest() {
        //given
        List<PlayerResultDto> resultDto = List.of(
                new PlayerResultDto("zuny", 5)
        );
        int savedId = gameResultDAO.save(new GameResultDto(10, "zuny"));

        //when
        playerResultDAO.save(savedId, resultDto);

        //then
        PlayerResultDto findResult = playerResultDAO.findAllByGameId(savedId).get(0);

        assertThat(findResult.getPosition())
                .isEqualTo(resultDto.get(0).getPosition());
        assertThat(findResult.getName())
                .isEqualTo(resultDto.get(0).getName());
    }
}
