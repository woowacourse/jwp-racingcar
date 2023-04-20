package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.RacingCarDto;

class InMemoryRacingCarDaoTest {
    private InMemoryRacingCarDao inMemoryRacingCarDao;

    @BeforeEach
    void setup() {
        inMemoryRacingCarDao = new InMemoryRacingCarDao();
        inMemoryRacingCarDao.insert(1L, "토리", 10);
    }

    @DisplayName("자동차 이름과 포지션을 저장할 수 있다.")
    @Test
    void Should_Save_When_InsertRacingCar() {
        assertDoesNotThrow(() -> inMemoryRacingCarDao.insert(1L, "토리", 9));
    }

    @DisplayName("게임 아이디를 통해 해당 게임을 진행한 자동차들의 이름과 포지션을 반환할 수 있다.")
    @Test
    void Should_GetGameResult_When_SelectByGameId() {
        List<RacingCarDto> racingCarResult = inMemoryRacingCarDao.selectByGameId(1);

        assertAll(
                () -> assertThat(racingCarResult.get(0).getName()).isEqualTo("토리"),
                () -> assertThat(racingCarResult.get(0).getPosition()).isEqualTo(10));
    }

}
