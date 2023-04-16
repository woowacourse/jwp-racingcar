package racingcar.dao.raceresult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

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

    @Test
    @DisplayName("경주 결과 요청을 받아서 저장하고 경주 결과 ID를 반환한다.")
    void save() {
        // given
        RacingCars racingCars = RacingCars.makeCars("성하,이오,코코닥");
        RaceResultRegisterRequest raceResultRegisterRequest = RaceResultRegisterRequest.create(5, racingCars);

        // when
        int savedPlayResultId = raceResultDao.save(raceResultRegisterRequest);

        // then
        assertThat(savedPlayResultId).isNotNull();
    }

    @Test
    @DisplayName("경주 결과 ID를 받아서 우승자를 조회한다.")
    void findWinnersByPlayResultId() {
        // given
        String names = "성하,이오,코코닥";
        RacingCars racingCars = RacingCars.makeCars(names);
        racingCars.moveAllCars(5, new RandomNumberGenerator());
        RaceResultRegisterRequest raceResultRegisterRequest = RaceResultRegisterRequest.create(5, racingCars);
        int savedPlayResultId = raceResultDao.save(raceResultRegisterRequest);

        // when
        List<String> nameList = Arrays.asList(names.split(","));
        String winners = raceResultDao.findWinnersByPlayResultId(savedPlayResultId);
        List<String> winnerList = Arrays.asList(winners.split(","));

        // then
        for (String winner : winnerList) {
            assertThat(nameList).contains(winner);
        }
    }
}
