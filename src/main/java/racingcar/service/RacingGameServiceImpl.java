package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameRepository;
import racingcar.domain.numbergenerator.NumberGenerator;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.WinnerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RacingGameServiceImpl implements RacingGameService {

    private final NumberGenerator numberGenerator;
    private final RacingGameRepository racingGameRepository;

    public RacingGameServiceImpl(
            final NumberGenerator numberGenerator,
            final RacingGameRepository racingGameRepository
    ) {
        this.numberGenerator = numberGenerator;
        this.racingGameRepository = racingGameRepository;
    }

    public GameResultDto play(final List<String> names, final int gameTime) {
        final Cars cars = new Cars(names.stream()
                .map(Car::new)
                .collect(Collectors.toList()));
        final var time = new GameTime(gameTime);
        final var racingGame = new RacingGame(cars, time);
        racingGame.play(numberGenerator);

        final var winners = racingGame.winners();
        racingGameRepository.save(racingGame);

        final List<CarDto> carDtos = racingGame.getCars().stream()
                .map(CarDto::fromCar)
                .collect(Collectors.toList());
        final List<WinnerDto> winnerDtos = WinnerDto.createWinnerDtos(winners);
        return new GameResultDto(carDtos, winnerDtos);
    }

    public List<GameResultDto> findAllResult() {
        final Map<Long, List<CarDto>> allCars = racingGameRepository.findAllCars();
        final Map<Long, List<WinnerDto>> allWinners = racingGameRepository.findAllWinners();

        List<GameResultDto> gameResultDtos = new ArrayList<>();
        addGameResultById(allCars, allWinners, gameResultDtos);

        return gameResultDtos;
    }

    private void addGameResultById(final Map<Long, List<CarDto>> allCars, final Map<Long, List<WinnerDto>> allWinners, final List<GameResultDto> gameResultDtos) {
        for (Map.Entry<Long, List<CarDto>> entry : allCars.entrySet()) {
            Long key = entry.getKey();
            List<CarDto> carDtos = allCars.get(key);
            List<WinnerDto> winnerDtos = allWinners.get(key);

            gameResultDtos.add(new GameResultDto(carDtos, winnerDtos));
        }
    }
}
