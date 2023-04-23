package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingCarRecordDao;
import racingcar.dao.RacingGameHistoryDao;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.NumberGenerator;
import racingcar.domain.game.RacingGame;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameDto;
import racingcar.repository.RacingGameRepository;

@SpringBootTest
@Transactional
public class RacingGameServiceTest {

    @Autowired
    private RacingGameService racingGameService;

    @Autowired
    private RacingGameRepository racingGameRepository;

    @Autowired
    private RacingGameHistoryDao racingGameHistoryDao;

    @Autowired
    private RacingCarRecordDao racingCarRecordDao;

    @DisplayName("게임을 진행하고 결과를 저장할 수 있다.")
    @Test
    void testPlayGameAndInsertData() {
        //given
        //when
        racingGameService.play(10, List.of("서브웨이", "브리", "로지"));
        //then
        List<RacingGame> racingGames = racingGameRepository.findAll();
        RacingGame racingGame = racingGames.get(0);
        List<RacingCar> racingCars = racingGame.getRacingCars();
        List<String> carNames = racingCars.stream().map(RacingCar::getName).collect(Collectors.toList());
        assertThat(carNames).containsExactly("서브웨이", "브리", "로지");
    }

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

    @Nested
    @DisplayName("Position테스트")
    class MockNumberGeneratorTest {
        private RacingGameService racingGameService;
        private NumberGenerator numberGenerator;

        @BeforeEach
        void setUp() {
            numberGenerator = new NumberGenerator() {
                Deque<Integer> numbers = new ArrayDeque<>(Arrays.asList(1, 4, 5));

                @Override
                public int generateNumber() {
                    return numbers.pollFirst();
                }
            };
            racingGameService = new RacingGameService(racingGameRepository, numberGenerator);
        }

        @DisplayName("게임을 진행하고 결과를 저장할 수 있다.")
        @Test
        void testPlayGameAndInsertData() {
            //given
            //when
            racingGameService.play(1, List.of("서브웨이", "브리", "로지"));
            //then
            List<RacingGame> racingGames = racingGameRepository.findAll();
            RacingGame racingGame = racingGames.get(0);
            List<String> carNames = racingGame.getRacingCars().stream().map(RacingCar::getName)
                    .collect(Collectors.toList());
            assertThat(carNames).containsExactly("서브웨이", "브리", "로지");
        }
    }

}
