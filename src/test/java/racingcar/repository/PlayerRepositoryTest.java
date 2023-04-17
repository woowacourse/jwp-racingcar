package racingcar.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import racingcar.domain.CarGroup;
import racingcar.repository.mapper.PlayerDtoMapper;

@SpringBootTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RacingGameRepository racingGameRepository;

    @DisplayName("플레이어 저장")
    @Test
    void save() {
        // given
        CarGroup carGroup = new CarGroup("저문,헤나");
        int racingGameId = racingGameRepository.save("저문,헤나", 10);

        // when
        boolean isSaved = playerRepository.save(carGroup, racingGameId);

        // then
        assertThat(isSaved).isTrue();
    }

    @DisplayName("플레이어 저장 후 반환된 아이디로 조회")
    @ParameterizedTest
    @CsvSource(value = {"저문,헤나:2", "저문,디노,우가:3", "저문,디노,우가,베베:4"}, delimiter = ':')
    void findAllById(String names, int gameCount) {
        // given
        CarGroup firstCarGroup = new CarGroup(names);
        int firstRacingGameId = racingGameRepository.save(names, gameCount);

        playerRepository.save(firstCarGroup, firstRacingGameId);

        // when
        List<PlayerDtoMapper> firstPlayerDtoMappers = playerRepository.findAllById(firstRacingGameId);

        // then
        assertThat(firstPlayerDtoMappers.size()).isEqualTo(gameCount);
    }
}
