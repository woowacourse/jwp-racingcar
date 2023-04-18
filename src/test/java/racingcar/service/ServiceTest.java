package racingcar.service;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import racingcar.dao.ConsoleCarDao;
import racingcar.dao.ConsoleGameDao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcar.dto.ResultDTO;
import racingcar.request.RacingGameRequest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ServiceTest {

    private RacingCarService racingCarService = new RacingCarService(new ConsoleGameDao(), new ConsoleCarDao());

    @Test
    void 이름과_시도횟수를_받아_우승자와_결과를_반환한다() {
        //given
        final RacingGameRequest racingGameRequest = new RacingGameRequest("jayon,gavi,huchu", 10);

        //when
        final ResultDTO result = racingCarService.play(List.of("jayon", "gavi", "huchu"), 10);

        //then
        assertAll(
                () -> assertThat(result.getWinners()).isNotEmpty(),
                () -> assertThat(result.getCarDTOs()).hasSize(3)
        );
    }

}
