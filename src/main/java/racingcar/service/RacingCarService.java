package racingcar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.PlayResultDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.CarResponseDto;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
import racingcar.view.util.TextParser;

@Service
public class RacingCarService {

    private final PlayResultDao playResultDao;
    private final CarDao carDao;

    public RacingCarService(final PlayResultDao playResultDao, final CarDao carDao) {
        this.playResultDao = playResultDao;
        this.carDao = carDao;
    }

    @Transactional
    public PlayResponseDto playGame(PlayRequestDto playRequest) {
        final RacingGame racingGame = createGame(playRequest.getNames());
        final int count = playRequest.getCount();

        racingGame.race(count);
        final List<Car> cars = racingGame.getCars();
        final List<String> winnerNames = racingGame.getWinnerNames();

        saveGame(count, CarDtoBuilder.dtos(cars, winnerNames));
        return new PlayResponseDto(CarDtoBuilder.responseDtos(cars), String.join(", ", winnerNames));
    }

    private RacingGame createGame(final String rawCarNames) {
        final List<String> carNames = TextParser.parseByDelimiter(rawCarNames, ",");
        return RacingGame.of(carNames);
    }

    private void saveGame(int count, List<CarDto> cars) {
        long savedId = playResultDao.insertAndReturnId(count);
        carDao.insert(savedId, cars);
    }

    public List<PlayResponseDto> findAllGames() {
        Map<Long, List<CarDto>> allCars = carDao.findAllCarsById();
        return allCars.values()
                .stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    private PlayResponseDto convertDto(List<CarDto> cars) {
        List<CarResponseDto> carResponseDtos = CarDtoBuilder.from(cars);
        String winners = extractWinnerNames(cars);
        return new PlayResponseDto(carResponseDtos, winners);
    }

    private String extractWinnerNames(final List<CarDto> cars) {
        return cars.stream()
                .filter(CarDto::isWinner)
                .map(CarDto::getName)
                .collect(Collectors.joining(", "));
    }
}
