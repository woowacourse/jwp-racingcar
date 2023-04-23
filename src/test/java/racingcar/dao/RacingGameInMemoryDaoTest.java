package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.dao.dto.RacingGameDto;

class RacingGameInMemoryDaoTest {

    private RacingGameInMemoryDao racingGameInMemoryDao;

    @BeforeEach
    void setUp() {
        racingGameInMemoryDao = new RacingGameInMemoryDao();
    }

    @DisplayName("레이싱 게임을 인메모리에 저장한다.")
    @Test
    void save() {
        // given
        String winners = "저문,헤나";
        int trial = 10;

        // when
        int savedId = racingGameInMemoryDao.save(winners, trial);

        // then
        assertThat(savedId).isEqualTo(1);
    }

    @DisplayName("아이디가 주어졌을 때 해당 데이터를 반환한다.")
    @Test
    void findById() {
        // given
        String winners = "저문,헤나";
        int trial = 10;
        int savedId = racingGameInMemoryDao.save(winners, trial);

        // when
        Optional<RacingGameDto> maybeRacingGameDtoMapper = racingGameInMemoryDao.findById(savedId);

        assertTrue(maybeRacingGameDtoMapper.isPresent());

        RacingGameDto racingGameDto = maybeRacingGameDtoMapper.get();

        // then
        assertThat(racingGameDto)
                .hasFieldOrPropertyWithValue("id", savedId)
                .hasFieldOrPropertyWithValue("winners", winners);
    }

    @DisplayName("전체 데이터를 반환한다.")
    @Test
    void findAll() {
        // given
        String firstWinners = "저문,헤나";
        String secondWinners = "저문,헤나,디노,우가";

        int firstTrial = 10;
        int secondTrial = 8;

        int firstSavedId = racingGameInMemoryDao.save(firstWinners, firstTrial);
        int secondSavedId = racingGameInMemoryDao.save(secondWinners, secondTrial);

        // when
        List<RacingGameDto> racingGameDtos = racingGameInMemoryDao.findAll();

        // then
        assertSoftly(softly -> {
            softly.assertThat(racingGameDtos.get(0))
                    .hasFieldOrPropertyWithValue("id", firstSavedId)
                    .hasFieldOrPropertyWithValue("winners", firstWinners);
            softly.assertThat(racingGameDtos.get(1))
                    .hasFieldOrPropertyWithValue("id", secondSavedId)
                    .hasFieldOrPropertyWithValue("winners", secondWinners);
        });
    }
}
