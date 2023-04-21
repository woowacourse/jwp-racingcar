package racingcar.dao.raceresult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;
import racingcar.domain.RacingCars;
import racingcar.util.RandomNumberGenerator;

@SpringBootTest
@Transactional
class RaceResultDaoTest {

    @Autowired
    private RaceResultDao raceResultDao;
    private String names;
    private RacingCars racingCars;
    private RaceResultRegisterRequest raceResultRegisterRequest;

    @BeforeEach
    void init() {
        String names = "성하,이오,코코닥";
        this.names = names;
        this.racingCars = RacingCars.makeCars(names);
        this.raceResultRegisterRequest = RaceResultRegisterRequest.create(5, racingCars);
    }

    @Test
    @DisplayName("경주 결과 요청을 받아서 저장하고 경주 결과 ID를 반환한다.")
    void save() {
        // when
        int savedPlayResultId = raceResultDao.save(raceResultRegisterRequest);

        // then
        assertThat(savedPlayResultId).isNotNull();
    }

    @Test
    @DisplayName("경주 결과 ID를 받아서 우승자를 조회한다.")
    void findWinnersByPlayResultId() {
        // given
        RaceResultRegisterRequest raceResultRegisterRequest = RaceResultRegisterRequest.create(5, racingCars);
        int savedPlayResultId = raceResultDao.save(raceResultRegisterRequest);

        // when
        racingCars.moveAllCars(5, new RandomNumberGenerator());
        List<String> nameList = Arrays.asList(names.split(","));
        String winners = raceResultDao.findWinnersByPlayResultId(savedPlayResultId);
        List<String> winnerList = Arrays.asList(winners.split(","));

        // then
        for (String winner : winnerList) {
            assertThat(nameList).contains(winner);
        }
    }

    @Test
    @DisplayName("모든 결과의 ID를 조회한다.")
    void findAllPlayResultId() {
        // when
        racingCars.moveAllCars(5, new RandomNumberGenerator());
        racingCars.moveAllCars(5, new RandomNumberGenerator());
        raceResultDao.save(raceResultRegisterRequest);
        raceResultDao.save(raceResultRegisterRequest);
        List<Integer> playResultIds = raceResultDao.findAllPlayResultId();

        // then
        assertThat(playResultIds.size()).isEqualTo(2);
        assertThat(playResultIds.get(0)).isNotNull();
        assertThat(playResultIds.get(1)).isNotNull();
    }
}
