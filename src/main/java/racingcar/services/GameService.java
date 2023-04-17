package racingcar.services;

import org.springframework.stereotype.Service;
import racingcar.dao.RacingGameRepository;
import racingcar.dto.CarDto;
import racingcar.dto.GameInformationDto;
import racingcar.dto.GameResultDto;
import racingcar.model.MoveCount;
import racingcar.model.RacingGame;
import racingcar.model.car.Cars;
import racingcar.model.manager.ThresholdCarMoveManager;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final RacingGameRepository racingGameRepository;

    public GameService(RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    public GameResultDto play(GameInformationDto gameInformationDto){
        Cars cars = Cars.from(gameInformationDto.getNames());
        MoveCount moveCount = MoveCount.from(gameInformationDto.getCount());
        RacingGame racingGame = new RacingGame(new ThresholdCarMoveManager(), cars, moveCount);
        racingGame.play();

        GameResultDto gameResultDto = new GameResultDto(racingGame.getWinners(), createCarDto(racingGame));
        racingGameRepository.saveGameResult(moveCount, gameResultDto);
        return gameResultDto;
    }

    private List<CarDto> createCarDto(RacingGame racingGame){
        return racingGame.getResult()
                .stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
