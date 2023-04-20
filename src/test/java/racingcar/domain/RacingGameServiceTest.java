package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingCarRecordDao;
import racingcar.dao.RacingGameHistoryDao;
import racingcar.domain.cars.RacingCar;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameDto;

@SpringBootTest
@Transactional
public class RacingGameServiceTest {

    @Autowired
    private RacingGameService racingGameService;

    @Autowired
    private RacingGameHistoryDao racingGameHistoryDao;

    @Autowired
    private RacingCarRecordDao racingCarRecordDao;


    @DisplayName("최신순으로 게임이력을 읽을 수 있다.")
    @Test
    void testReadAppropriateOrder() {
        //given
        Long historyId = racingGameHistoryDao.insert(10, LocalDateTime.now());
        racingCarRecordDao.insert(historyId, new RacingCar("로지", 10), true);
        racingCarRecordDao.insert(historyId, new RacingCar("키아라", 5), false);

        Long otherHistoryId = racingGameHistoryDao.insert(5, LocalDateTime.now().minusDays(5));
        racingCarRecordDao.insert(otherHistoryId, new RacingCar("서브웨이", 5), true);
        racingCarRecordDao.insert(otherHistoryId, new RacingCar("로지", 3), false);

        Long anotherHistoryId = racingGameHistoryDao.insert(5, LocalDateTime.now().minusDays(10));
        racingCarRecordDao.insert(anotherHistoryId, new RacingCar("헤이", 5), true);
        racingCarRecordDao.insert(anotherHistoryId, new RacingCar("로지", 5), false);

        //when
        List<RacingGameDto> racingGameDtos = racingGameService.readGameHistory();

        //then
        assertThat(racingGameDtos.get(0).getWinnerNames()).containsOnly("로지");
        assertThat(racingGameDtos.get(1).getWinnerNames()).containsOnly("서브웨이");
        assertThat(racingGameDtos.get(2).getWinnerNames()).containsExactly("헤이", "로지");
    }

    @DisplayName("읽어오기 기능을 할 수 있다.")
    @Test
    void testReadHistory() {
        //given
        Long historyId = racingGameHistoryDao.insert(10, LocalDateTime.now());
        String playerName = "로지";
        String otherPlayerName = "키아라";
        racingCarRecordDao.insert(historyId, new RacingCar(playerName, 10), true);
        racingCarRecordDao.insert(historyId, new RacingCar(otherPlayerName, 5), false);

        //when
        List<RacingGameDto> racingGameDtos = racingGameService.readGameHistory();

        //then
        RacingGameDto racingGameDto = racingGameDtos.get(0);
        assertThat(racingGameDto.getWinnerNames()).containsOnly(playerName);
        assertThat(racingGameDto.getRacingCars().stream().map(RacingCarDto::getName)).contains(playerName,
                otherPlayerName);
        RacingCarDto player = racingGameDto.getRacingCars().stream()
                .filter(car -> car.getName().equals(playerName))
                .findFirst().get();
        RacingCarDto otherPlayer = racingGameDto.getRacingCars().stream()
                .filter(car -> car.getName().equals(otherPlayerName))
                .findFirst().get();
        assertThat(player.getPosition()).isEqualTo(10);
        assertThat(otherPlayer.getPosition()).isEqualTo(5);

    }
}
