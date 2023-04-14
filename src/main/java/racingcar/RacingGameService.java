package racingcar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.domain.movingstrategy.DefaultMovingStrategy;
import racingcar.entity.CarEntity;
import racingcar.entity.GameResultEntity;
import racingcar.repository.CarRepository;
import racingcar.repository.GameResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final GameResultRepository gameResultRepository;
    private final CarRepository carRepository;

    public RacingGameService(final GameResultRepository gameResultRepository, final CarRepository carRepository) {
        this.gameResultRepository = gameResultRepository;
        this.carRepository = carRepository;
    }

    @Transactional
    public GameResultResponseDto getResult(final UserRequestDto inputDto) {
        final RacingGame racingGame = mapToRacingGame(inputDto);
        final Long gameResultId = gameResultRepository.save(new GameResultEntity(inputDto.getCount()));
        final List<CarEntity> carEntities = startAndGetResult(racingGame, gameResultId);
        carRepository.saveAll(carEntities);

        return new GameResultResponseDto(carEntities);
    }

    private RacingGame mapToRacingGame(final UserRequestDto inputDto) {
        final String names = inputDto.getNames();
        final List<String> splitNames = List.of(names.split(","));
        final List<Name> nameList = splitNames.stream()
                .map(Name::of)
                .collect(Collectors.toList());
        final TryCount tryCount = new TryCount(inputDto.getCount());

        return new RacingGame(nameList, tryCount);
    }

    private List<CarEntity> startAndGetResult(final RacingGame racingGame, final Long gameResultId) {
        final List<Cars> result = racingGame.start(new DefaultMovingStrategy());
        final Cars finalResult = result.get(result.size() - 1);
        final Cars winnersResult = racingGame.decideWinners();

        return finalResult.getCars()
                .stream()
                .map(car -> new CarEntity(car.getNameValue(), car.getPositionValue(), checkWinner(car, winnersResult), gameResultId))
                .collect(Collectors.toList());
    }

    private boolean checkWinner(final Car currentCar, final Cars winnersResult) {
        return winnersResult.getCars().contains(currentCar);
    }
}
