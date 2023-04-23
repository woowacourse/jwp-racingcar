package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.raceresult.RaceResultDao;
import racingcar.domain.RacingCars;
import racingcar.util.RandomNumberGenerator;

@JdbcTest
class RaceResultRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RaceResultRepository raceResultRepository;
    private String names;
    private RacingCars racingCars;
    private int trialCount;


    @BeforeEach
    void init() {
        this.raceResultRepository = new RaceResultRepository(new RaceResultDao(jdbcTemplate));
        this.names = "성하,토니";
        this.racingCars = RacingCars.makeCars(names);
        this.trialCount = 5;
    }

    @Test
    @DisplayName("경주한 자동차와 시도 횟수를 받아서 DB 저장 명령을 호출한다.")
    void save() {
        // when
        int savedId = raceResultRepository.save(5, racingCars);

        // then
        assertThat(savedId).isNotNull();
    }

    @Test
    @DisplayName("경주 결과 ID를 받아서 우승자 조회 명령을 호출한다.")
    void findWinnersByPlayResultId() {
        // when
        int savedPlayResultId = raceResultRepository.save(trialCount, racingCars);
        racingCars.moveAllCars(5, new RandomNumberGenerator());
        List<String> nameList = Arrays.asList(names.split(","));
        String winners = raceResultRepository.findWinnersByPlayResultId(savedPlayResultId);
        List<String> winnerList = Arrays.asList(winners.split(","));

        // then
        for (String winner : winnerList) {
            assertThat(nameList).contains(winner);
        }
    }

    @Test
    @DisplayName("모든 결과의 ID 조회 명령을 호출한다.")
    void findAllPlayResultId() {
        // when
        racingCars.moveAllCars(trialCount, new RandomNumberGenerator());
        raceResultRepository.save(trialCount, racingCars);
        raceResultRepository.save(trialCount, racingCars);
        List<Integer> playResultIds = raceResultRepository.findAllPlayResultId();

        // then
        assertThat(playResultIds.size()).isEqualTo(2);
        assertThat(playResultIds.get(0)).isNotNull();
        assertThat(playResultIds.get(1)).isNotNull();
    }
}
