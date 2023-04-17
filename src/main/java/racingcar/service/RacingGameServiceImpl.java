package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameRepository;
import racingcar.domain.Winner;
import racingcar.domain.Winners;
import racingcar.domain.numbergenerator.NumberGenerator;
import racingcar.dto.GameResultDto;
import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
public class RacingGameServiceImpl implements RacingGameService {

    private final NumberGenerator numberGenerator;
    private final RacingGameRepository racingGameRepository;

    public RacingGameServiceImpl(final NumberGenerator numberGenerator, final RacingGameRepository racingGameRepository) {
        this.numberGenerator = numberGenerator;
        this.racingGameRepository = racingGameRepository;
    }

    public GameResultDto play(final List<String> names, final int gameTime) {
        final Cars cars = new Cars(names.stream()
                .map(Car::new)
                .collect(Collectors.toList()));
        final GameTime time = new GameTime(String.valueOf(gameTime));
        final RacingGame racingGame = new RacingGame(cars, time);
        racingGame.play(numberGenerator);
        racingGameRepository.save(racingGame);
        final Winners result = racingGame.winners();
        return new GameResultDto(racingGame.getCars(), result);
    }

    public List<GameResultDto> findAllResult() {
        final Map<Long, List<CarEntity>> allCars = racingGameRepository.findAllCars();
        final Map<Long, List<WinnerEntity>> allWinners = racingGameRepository.findAllWinners();

        List<GameResultDto> gameResultDtos = new ArrayList<>();
        addGameResultById(allCars, allWinners, gameResultDtos);

        return gameResultDtos;
    }

    private void addGameResultById(final Map<Long, List<CarEntity>> allCars, final Map<Long, List<WinnerEntity>> allWinners, final List<GameResultDto> gameResultDtos) {
        for (Map.Entry<Long, List<CarEntity>> entry : allCars.entrySet()) {
            Long key = entry.getKey();
            List<CarEntity> carEntities = allCars.get(key);
            List<WinnerEntity> winnerEntities = allWinners.get(key);

            List<Car> cars = toCars(carEntities);

            List<Winner> winners = toWinners(winnerEntities);

            gameResultDtos.add(new GameResultDto(cars, new Winners(winners)));
        }
    }

    private List<Car> toCars(final List<CarEntity> carEntities) {
        return carEntities.stream()
                .map(carEntity -> new Car(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toList());
    }

    private List<Winner> toWinners(final List<WinnerEntity> winnerEntities) {
        return winnerEntities.stream()
                .map(winnerEntity -> new Winner(winnerEntity.getName()))
                .collect(Collectors.toList());
    }
}
