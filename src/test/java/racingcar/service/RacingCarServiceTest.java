package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.dao.RacingCarDao;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class RacingCarServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingCarService racingCarService;

    @BeforeEach
    public void setUp() {
        racingCarService = new RacingCarService(new RacingCarDao(jdbcTemplate));
    }

    @Test
    void plays() {
        GameRequestDtoForPlays gameRequestDtoForPlays = new GameRequestDtoForPlays("메투,매투", "10");
        GameResponseDto result = racingCarService.plays(gameRequestDtoForPlays);

        assertThat(result.getWinners()).isNotNull();
        assertThat(result.getRacingCars()).isNotNull();
    }

}
