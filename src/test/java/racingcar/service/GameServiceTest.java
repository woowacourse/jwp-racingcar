package racingcar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.dto.GameRecordResponseDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.util.NumberGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private NumberGenerator numberGenerator;
    @Mock
    private GameDao gameDao;
    @Mock
    private CarDao carDao;

    @InjectMocks
    private GameService gameService;

    @Test
    void executeRacingGame() {
        String names = "ocean,mint";
        int tryCount = 5;
        RacingGameRequestDto racingGameRequestDto = new RacingGameRequestDto(names, tryCount);

        RacingGameResponseDto result = gameService.executeRacingGame(racingGameRequestDto);

        assertThat(result.getWinners()).isEqualTo(names);
    }

    @Test
    void getGameRecord() {
        List<GameEntity> gameEntities = List.of(new GameEntity(1, 5));
        List<CarEntity> carEntities = List.of(new CarEntity(1, "ocean", 2));
        when(gameDao.findAll()).thenReturn(gameEntities);
        when(carDao.findAllById(1)).thenReturn(carEntities);

        GameRecordResponseDto gameRecordResponseDto = new GameRecordResponseDto(carEntities, carEntities);

        assertThat(gameService.getGameRecord().get(0)).usingRecursiveComparison().isEqualTo(gameRecordResponseDto);
    }
}
